package pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.timetable.business.repository.ScheduleEntityRepository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.ScheduleEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.port.ScheduleCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.timetable.infrastructure.entity.QScheduleEntity.scheduleEntity;

@Repository
@RequiredArgsConstructor
public class ScheduleEntityRepositoryImpl implements ScheduleEntityRepository {

    @PersistenceContext
    private EntityManager em;

    private final ScheduleCrudRepository scheduleCrudRepository;

    @Override
    @Transactional
    public ScheduleProjection save(ScheduleProjection scheduleProjection) {
        val entityToPersist = ScheduleEntity.of(scheduleProjection, em);
        em.merge(entityToPersist);
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
    public List<ScheduleProjection> findById(Long id) {
        JPAQuery<ScheduleEntity> query = new JPAQuery<>(em);
        return query.from(scheduleEntity).where(scheduleEntity.id.eq(id)).fetch()
                .stream()
                .map(ScheduleEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {

        JPADeleteClause deleteClause = new JPADeleteClause(em,scheduleEntity);
        deleteClause.where(scheduleEntity.id.eq(id)).execute();
    }

    @Override
    public void deleteAll() {
        scheduleCrudRepository.deleteAll();
    }
}
