package pl.kompikownia.pksmanager.usermanager.infrastructure.repository;

import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.UserEntity;
import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedUserProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public CreatedUserProjection saveUser(UserData userData) {
        val entity = UserEntity.of(userData);
        entityManager.persist(entity);
        return CreatedUserProjection.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .securityUserId(entity.getSecurityUserId())
                .build();
    }
}
