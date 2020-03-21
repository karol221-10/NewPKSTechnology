package pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.QTown;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.Town;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.TownRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TownRepositoryImpl implements TownRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Town save(Town town) {
        em.persist(town);
        return town;
    }

    @Override
    public List<Town> findAll() {
        JPAQuery<Town> query = new JPAQuery<>(em);

        QTown town = QTown.town;

        return query.from(town).orderBy(town.id.asc()).fetch();
    }

    @Override
    public List<Town> findByName(String name) {
        JPAQuery<Town> query = new JPAQuery<>(em);
        QTown town = QTown.town;
        return query.from(town).where(town.name.eq(name)).fetch();
    }

    @Override
    public void deleteById(Long id) {
        QTown town = QTown.town;
        JPADeleteClause deleteClause = new JPADeleteClause(em,town);
        deleteClause.where(town.id.eq(id)).execute();
    }


}
