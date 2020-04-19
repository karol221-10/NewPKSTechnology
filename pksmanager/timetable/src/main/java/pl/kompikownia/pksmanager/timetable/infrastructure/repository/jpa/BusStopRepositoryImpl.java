package pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;
import pl.kompikownia.pksmanager.timetable.business.repository.BusStopRepository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.BusStopEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.QBusStopEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.ScheduleEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.port.BusStopCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class BusStopRepositoryImpl implements BusStopRepository {

    @PersistenceContext
    private EntityManager em;

    private final BusStopCrudRepository busStopCrudRepository;

    @Override
    @Transactional
    public BusStopProjection save(BusStopProjection busStopProjection) {
        val entityToPersist = BusStopEntity.of(em, busStopProjection);
        val parentEntity = em.getReference(ScheduleEntity.class, busStopProjection.getScheduleId());
        parentEntity.getBusStopEntities().add(entityToPersist);
        em.merge(entityToPersist);
        em.merge(parentEntity);
        return entityToPersist.toProjection();
    }

    @Override
    public List<BusStopProjection> findAll() {
        JPAQuery<BusStopEntity> query = new JPAQuery<>(em);

        QBusStopEntity busStopEntity = QBusStopEntity.busStopEntity;

        return query.from(busStopEntity).orderBy(busStopEntity.id.asc()).fetch()
                .stream()
                .map(BusStopEntity::toProjection)
                .collect(Collectors.toList());

    }

    @Override
    public List<BusStopProjection> findByCourseId(Long ScheduleId) {
        JPAQuery<BusStopEntity> query = new JPAQuery<>(em);

        QBusStopEntity busStopEntity = QBusStopEntity.busStopEntity;

        return query.from(busStopEntity)
                .where(busStopEntity.schedule.id.eq(ScheduleId))
                .fetch()
                .stream()
                .map(BusStopEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        QBusStopEntity busStopEntity = QBusStopEntity.busStopEntity;
        JPADeleteClause deleteClause = new JPADeleteClause(em,busStopEntity);
        deleteClause.where(busStopEntity.id.eq(id)).execute();
    }

    @Override
    public void deleteAll() {
        busStopCrudRepository.deleteAll();
    }
}
