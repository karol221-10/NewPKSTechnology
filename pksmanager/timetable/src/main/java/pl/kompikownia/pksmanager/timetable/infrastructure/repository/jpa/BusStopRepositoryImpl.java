package pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.BusStopEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.QBusStopEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.port.BusStopRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class BusStopRepositoryImpl implements BusStopRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public BusStopEntity save(BusStopEntity busStopEntity) {
        em.persist(busStopEntity);
        return busStopEntity;
    }

    @Override
    public List<BusStopEntity> findAll() {
        JPAQuery<BusStopEntity> query = new JPAQuery<>(em);

        QBusStopEntity busStopEntity = QBusStopEntity.busStopEntity;

        return query.from(busStopEntity).orderBy(busStopEntity.id.asc()).fetch();

    }

    @Override
    public List<BusStopEntity> findByCourseId(Long ScheduleId) {
        JPAQuery<BusStopEntity> query = new JPAQuery<>(em);

        QBusStopEntity busStopEntity = QBusStopEntity.busStopEntity;

        return query.from(busStopEntity).where(busStopEntity.schedule.id.eq(ScheduleId)).fetch();
    }

    @Override
    public void deleteById(Long id) {
        QBusStopEntity busStopEntity = QBusStopEntity.busStopEntity;
        JPADeleteClause deleteClause = new JPADeleteClause(em,busStopEntity);
        deleteClause.where(busStopEntity.id.eq(id)).execute();
    }
}
