package pl.kompikownia.pksmanager.security.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.kompikownia.pksmanager.security.business.exception.CannotFindUserException;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserProjection;
import pl.kompikownia.pksmanager.security.business.projection.NewUserData;
import pl.kompikownia.pksmanager.security.business.projection.UserWithPermissionProjection;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;
import pl.kompikownia.pksmanager.security.infrastructure.entity.PermissionEntity;
import pl.kompikownia.pksmanager.security.infrastructure.entity.RoleEntity;
import pl.kompikownia.pksmanager.security.infrastructure.entity.UserEntity;
import pl.kompikownia.pksmanager.security.infrastructure.model.UserDetailsModel;
import pl.kompikownia.pksmanager.security.infrastructure.repository.port.UserAuthenticationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.security.infrastructure.entity.QUserEntity.userEntity;

@Service
public class UserRepositoryImpl implements UserAuthenticationRepository, UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserDetailsModel findByUsername(String username) {
        JPAQuery<UserEntity> query = new JPAQuery<>(entityManager);
        val entity = query.from(userEntity).where(userEntity.username.eq(username)).fetchFirst();
        return UserDetailsModel.of(entity);
    }

    @Override
    public UserProjection putNewUser(NewUserData newUserData) {
        val entityToPersist = UserEntity.of(entityManager, newUserData);
        entityManager.persist(entityToPersist);
        newUserData.getRolesId().forEach(roleId -> {
            val roleEntity = entityManager.getReference(RoleEntity.class, roleId);
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
        JPAQuery<UserEntity> query = new JPAQuery<>(entityManager);

        val user = query.from(userEntity)
                .where(userEntity.username.eq(username))
                .where(userEntity.password.eq(password))
                .fetchFirst();
        if(user == null) {
            throw new CannotFindUserException("Cannot find user "+username + " with given password");
        }
        return user.getId();
    }

    @Override
    @Transactional
    public UserWithPermissionProjection getUserWithPermissionsById(Long id) {
        val userEntity = entityManager.getReference(UserEntity.class, id);
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
}
