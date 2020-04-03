package pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.QScheduleEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.ScheduleEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.port.ScheduleEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ScheduleEntityRepositoryImpl implements ScheduleEntityRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ScheduleEntity save(ScheduleEntity scheduleEntity) {
        em.persist(scheduleEntity);
        return scheduleEntity;
    }

    @Override
    public List<ScheduleEntity> findAll() {
        JPAQuery<ScheduleEntity> query = new JPAQuery<>(em);

        QScheduleEntity scheduleEntity = QScheduleEntity.scheduleEntity;

        return query.from(scheduleEntity).orderBy(scheduleEntity.id.asc()).fetch();

    }

    @Override
    public List<ScheduleEntity> findById(Long id) {
        JPAQuery<ScheduleEntity> query = new JPAQuery<>(em);

        QScheduleEntity scheduleEntity = QScheduleEntity.scheduleEntity;

        return query.from(scheduleEntity).where(scheduleEntity.id.eq(id)).fetch();
    }

    @Override
    public void deleteById(Long id) {

        QScheduleEntity scheduleEntity = QScheduleEntity.scheduleEntity;
        JPADeleteClause deleteClause = new JPADeleteClause(em,scheduleEntity);
        deleteClause.where(scheduleEntity.id.eq(id)).execute();
    }
}
