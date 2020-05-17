package pl.kompikownia.pksmanager.security.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.kompikownia.pksmanager.security.business.exception.CannotFindUserException;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserProjection;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;
import pl.kompikownia.pksmanager.security.business.projection.NewUserData;
import pl.kompikownia.pksmanager.security.business.projection.UserWithPermissionProjection;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;
import pl.kompikownia.pksmanager.security.infrastructure.entity.PermissionEntity;
import pl.kompikownia.pksmanager.security.infrastructure.entity.RoleEntity;
import pl.kompikownia.pksmanager.security.infrastructure.entity.SecurityUserEntity;
import pl.kompikownia.pksmanager.security.infrastructure.mapper.UserWithLoginDataMapper;
import pl.kompikownia.pksmanager.security.infrastructure.model.UserDetailsModel;
import pl.kompikownia.pksmanager.security.infrastructure.repository.port.UserAuthenticationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.security.infrastructure.entity.QSecurityUserEntity.securityUserEntity;


@Service
public class UserSecurityRepositoryImpl implements UserAuthenticationRepository, UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserDetailsModel findByUsername(String username) {
        JPAQuery<SecurityUserEntity> query = new JPAQuery<>(entityManager);
        val entity = query.from(securityUserEntity).where(securityUserEntity.username.eq(username)).fetchFirst();
        return UserDetailsModel.of(entity);
    }

    @Override
    @Transactional
    public UserProjection putNewUser(NewUserData newUserData) {
        val entityToPersist = SecurityUserEntity.of(entityManager, newUserData);
        entityToPersist.setActive(true);
        entityManager.persist(entityToPersist);
        newUserData.getRolesId().forEach(roleId -> {
            val roleEntity = entityManager.getReference(RoleEntity.class, Long.parseLong(roleId));
            roleEntity.getUsers().add(entityToPersist);
        });
        return UserProjection.builder()
                .id(entityToPersist.getId().toString())
                .username(entityToPersist.getUsername())
                .rolesId(entityToPersist.getRoles().stream()
                        .map(RoleEntity::getId)
                        .map(Object::toString)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Long getUserByUsernameAndPassword(String username, String password) {
        JPAQuery<SecurityUserEntity> query = new JPAQuery<>(entityManager);

        val user = query.from(securityUserEntity)
                .where(securityUserEntity.username.eq(username))
                .where(securityUserEntity.password.eq(password))
                .fetchFirst();
        if(user == null) {
            throw new CannotFindUserException("Cannot find user "+username + " with given password");
        }
        return user.getId();
    }

    @Override
    @Transactional
    public UserWithPermissionProjection getUserWithPermissionsById(Long id) {
        val userEntity = entityManager.getReference(SecurityUserEntity.class, id);
        return UserWithPermissionProjection.builder()
                .username(userEntity.getUsername())
                .permissionNames(userEntity.getRoles().stream()
                        .flatMap(roleEntity -> roleEntity.getPermissions().stream())
                        .map(PermissionEntity::getPermissionName)
                        .distinct()
                        .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public List<UserWithLoginData> getUsersByIds(List<String> ids) {
        JPAQuery<SecurityUserEntity> query = new JPAQuery<>(entityManager);

        val idslong = ids.stream().map(Long::parseLong)
                .collect(Collectors.toList());

        val users = query.from(securityUserEntity)
                .where(securityUserEntity.id.in(idslong))
                .fetchAll()
                .fetch();

        return users.stream()
                .map(UserWithLoginDataMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deactivateUser(String userId) {
        val entity = entityManager.getReference(SecurityUserEntity.class, Long.parseLong(userId));
        if(entity.getId() == null) {
            throw new IllegalArgumentException("Security user with id "+ userId + " does not exists!");
        }
        entity.setActive(false);
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public boolean isUserActive(String id) {
        val entity = entityManager.getReference(SecurityUserEntity.class, Long.parseLong(id));
        if(entity.getId() == null) {
            throw new IllegalArgumentException("Security user with id "+ id + " does not exists!");
        }
        return entity.getActive();
    }
}
