package pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.ScheduleRepository;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.BusStopEntity;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.ScheduleEntity;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository.jpa.ScheduleCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.QScheduleEntity.scheduleEntity;


@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    @PersistenceContext
    private EntityManager em;

    private final ScheduleCrudRepository scheduleCrudRepository;

    @Override
    @Transactional
    public ScheduleProjection save(ScheduleProjection scheduleProjection) {
        val entityToPersist = ScheduleEntity.of(scheduleProjection, em);
        em.persist(entityToPersist);
        em.flush();
        return entityToPersist.toProjection();
    }

    @Override
    public List<ScheduleProjection> findCoursesByTownId(Long id1,Long id2) {
        JPAQuery<ScheduleEntity> query = new JPAQuery<>(em);
        return query.from(scheduleEntity)
                .where(
                        scheduleEntity.busStopEntities.any().town.id.eq(id1)
                                .and(
                                        scheduleEntity.busStopEntities.any().town.id.eq(id2)))
                .fetch()
                .stream()
                .map(ScheduleEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleProjection> findAll() {
        JPAQuery<ScheduleEntity> query = new JPAQuery<>(em);
        return query.from(scheduleEntity).orderBy(scheduleEntity.id.asc()).fetch()
                .stream()
                .map(ScheduleEntity::toProjection)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public ScheduleProjection update(ScheduleProjection toUpdate) {
        val entity = em.find(ScheduleEntity.class, toUpdate.getId());
        entity.setBusId(toUpdate.getBusId());
        entity.setWorkerId(toUpdate.getWorkerId());
        entity.setActive(toUpdate.isActive());
        val newEntity = em.merge(entity);
        return newEntity.toProjection();
    }

    @Override
    public ScheduleProjection findById(Long id) {
        val entity = em.find(ScheduleEntity.class, id);
        return entity.toProjection();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        val entity = em.find(ScheduleEntity.class, id);
        entity.getBusStopEntities().forEach(busStopEntity ->
                em.remove(busStopEntity)
        );
        em.remove(entity);
        em.flush();
    }

    @Override
    public void deleteAll() {
        scheduleCrudRepository.deleteAll();
    }
}
