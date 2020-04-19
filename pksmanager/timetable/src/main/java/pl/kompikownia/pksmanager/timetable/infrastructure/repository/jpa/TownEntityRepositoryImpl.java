package pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.QTownEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;
import pl.kompikownia.pksmanager.timetable.business.repository.TownEntityRepository;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.port.TownCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TownEntityRepositoryImpl implements TownEntityRepository {

    @PersistenceContext
    private EntityManager em;

    private final TownCrudRepository townCrudRepository;


    @Override
    public TownEntity save(TownEntity townEntity) {
        return townCrudRepository.save(townEntity);
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

    @Override
    public void deleteAll() {
        townCrudRepository.deleteAll();
    }
}
