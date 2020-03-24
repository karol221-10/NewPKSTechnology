package pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.QTownEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.TownEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TownEntityRepositoryImpl implements TownEntityRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public TownEntity save(TownEntity townEntity) {
        em.persist(townEntity);
        return townEntity;
    }

    @Override
    public List<TownEntity> findAll() {
        JPAQuery<TownEntity> query = new JPAQuery<>(em);

        QTownEntity townEntity = QTownEntity.townEntity;

        return query.from(townEntity).orderBy(townEntity.id.asc()).fetch();
    }

    @Override
    public List<TownEntity> findByName(String name) {
        JPAQuery<TownEntity> query = new JPAQuery<>(em);
        QTownEntity townEntity = QTownEntity.townEntity;
        return query.from(townEntity).where(townEntity.name.eq(name)).fetch();
    }

    @Override
    public void deleteById(Long id) {
        QTownEntity townEntity = QTownEntity.townEntity;
        JPADeleteClause deleteClause = new JPADeleteClause(em,townEntity);
        deleteClause.where(townEntity.id.eq(id)).execute();
    }


}
