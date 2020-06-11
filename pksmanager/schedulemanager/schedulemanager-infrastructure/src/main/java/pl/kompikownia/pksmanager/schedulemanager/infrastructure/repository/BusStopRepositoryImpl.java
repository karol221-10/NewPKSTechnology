package pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.BusStopEntity;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.QBusStopEntity;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.ScheduleEntity;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.TownEntity;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository.jpa.BusStopCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.QBusStopEntity.busStopEntity;


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
        val parentEntity = em.getReference(ScheduleEntity.class, Long.parseLong(busStopProjection.getScheduleId()));
        parentEntity.getBusStopEntities().add(entityToPersist);
        em.persist(entityToPersist);
        em.persist(parentEntity);
        return entityToPersist.toProjection(parentEntity.getId());
    }

    @Override
    @Transactional
    public BusStopProjection update(BusStopProjection busStopProjection) {
        val entity = em.find(BusStopEntity.class, busStopProjection.getId());
        entity.setTown(em.find(TownEntity.class, busStopProjection.getTownId()));
        entity.setArrivalDate(busStopProjection.getArrivalDate());
        entity.setDepartureDate(busStopProjection.getDepartureDate());
        entity.setPrice(busStopProjection.getPrice());
        entity.setDistanceFromPrev(busStopProjection.getDistanceFromPrev());
        val savedEntity = em.merge(entity);
        return savedEntity.toProjection();
    }

    @Override
    public BusStopProjection findById(String id) {
        val entity = em.find(BusStopEntity.class, Long.parseLong(id));
        return entity.toProjection();
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

        return query.from(busStopEntity)
                .where(busStopEntity.schedule.id.eq(ScheduleId))
                .fetch()
                .stream()
                .map(BusStopEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BusStopProjection> getBusStopBeforeDateTime(Long scheduleId, LocalDateTime dateTime) {

        JPAQuery<BusStopEntity> query = new JPAQuery<>(em);

        val result = query.from(busStopEntity)
                .where(busStopEntity.schedule.id.eq(scheduleId))
                .where(busStopEntity.arrivalDate.before(dateTime))
                .orderBy(busStopEntity.arrivalDate.desc())
                .fetchFirst();

        if(result == null) return Optional.empty();
        return Optional.of(result.toProjection());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        val entity = em.find(BusStopEntity.class, id);
        val parentEntity = entity.getSchedule();
        parentEntity.getBusStopEntities().remove(entity);
        em.remove(entity);
        em.flush();
    }

    @Override
    public void deleteAll() {
        busStopCrudRepository.deleteAll();
    }
}
