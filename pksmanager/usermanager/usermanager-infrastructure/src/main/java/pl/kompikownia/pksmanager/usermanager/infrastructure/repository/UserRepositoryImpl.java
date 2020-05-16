package pl.kompikownia.pksmanager.usermanager.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.usermanager.business.projection.SavedWorkerInDbProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.UserEntity;
import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedUserProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.repository.UserRepository;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.WorkerEntity;
import pl.kompikownia.pksmanager.usermanager.infrastructure.mapper.UserDataMapper;
import pl.kompikownia.pksmanager.usermanager.infrastructure.mapper.WorkerDataMapper;
import pl.kompikownia.pksmanager.usermanager.infrastructure.repository.jpa.UserRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.usermanager.infrastructure.entity.QUserEntity.userEntity;
import static pl.kompikownia.pksmanager.usermanager.infrastructure.entity.QWorkerEntity.workerEntity;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private UserRepositoryJPA userRepositoryJPA;

    @Override
    @Transactional
    public CreatedUserProjection saveUser(UserData userData) {
        val entity = UserEntity.of(userData);
        entity.setActive(true);
        entityManager.persist(entity);
        return CreatedUserProjection.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .securityUserId(entity.getSecurityUserId())
                .build();
    }

    @Override
    @Transactional
    public SavedWorkerInDbProjection saveWorker(WorkerData workerData) {
        val workerEntity = WorkerEntity.of(workerData, entityManager);
        entityManager.persist(workerEntity);
        return SavedWorkerInDbProjection.builder()
                .id(workerEntity.getId())
                .driverLicenseNumber(workerEntity.getDriverLicenseNumber())
                .personIdNumber(workerEntity.getPersonIdNumber())
                .pesel(workerEntity.getPesel())
                .build();
    }

    @Override
    public List<UserData> getUserList() {
        JPAQuery<UserEntity> query = new JPAQuery<>(entityManager);
        val userEntities = query.from(userEntity).where(userEntity.active.isTrue())
                .fetchAll()
                .fetch();
        return userEntities.stream()
                .map(UserDataMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkerData> getWorkersList() {
        JPAQuery<WorkerEntity> query = new JPAQuery<>(entityManager);

        val workers = query.from(workerEntity)
                .innerJoin(workerEntity.userEntity, userEntity)
                .where(userEntity.active.isTrue())
                .fetchAll()
                .fetch();

        return workers.stream()
                .map(WorkerDataMapper::map)
                .collect(Collectors.toList());
    }
}
