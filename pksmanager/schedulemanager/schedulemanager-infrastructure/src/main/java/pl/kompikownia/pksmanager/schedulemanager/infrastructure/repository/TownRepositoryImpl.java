package pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.TownRepository;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository.jpa.TownCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TownRepositoryImpl implements TownRepository {

    @PersistenceContext
    private EntityManager em;

    private final TownCrudRepository townCrudRepository;

    @Override
    public TownProjection save(TownProjection townEntity) {

        //return townCrudRepository.save(townEntity);
        return null;
    }

    @Override
    public List<TownProjection> findAll() {
        JPAQuery<TownProjection> query = new JPAQuery<>(em);

       // return query.from(townEntity).orderBy(townEntity.id.asc()).fetch();
        return null;
    }

    @Override
    public List<TownProjection> findByName(String name) {
      /*  JPAQuery<TownEntity> query = new JPAQuery<>(em);
        QTownEntity townEntity = QTownEntity.townEntity;
        return query.from(townEntity).where(townEntity.name.eq(name)).fetch();*/
      return null;
    }

    @Override
    public void deleteById(Long id) {
        /*QTownEntity townEntity = QTownEntity.townEntity;
        JPADeleteClause deleteClause = new JPADeleteClause(em,townEntity);
        deleteClause.where(townEntity.id.eq(id)).execute();*/
    }

    @Override
    public void deleteAll() {
        townCrudRepository.deleteAll();
    }
}
