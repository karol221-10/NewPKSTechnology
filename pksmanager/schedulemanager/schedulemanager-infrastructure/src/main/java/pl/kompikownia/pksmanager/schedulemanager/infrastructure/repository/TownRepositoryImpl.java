package pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.TownRepository;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.TownEntity;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository.jpa.TownCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.QTownEntity.townEntity;

@Repository
@RequiredArgsConstructor
@Transactional
public class TownRepositoryImpl implements TownRepository {

    @PersistenceContext
    private EntityManager em;

    private final TownCrudRepository townCrudRepository;

    @Override
    public TownProjection save(TownProjection townProjection) {
        val entityToPersist = TownEntity.of(em,townProjection);
        em.persist(entityToPersist);
        em.flush();

        return entityToPersist.toProjection();
    }

    @Override
    public TownProjection findById(Long id) {
        JPAQuery<TownEntity> query = new JPAQuery<>(em);
        return query.from(townEntity)
                .where(townEntity.id.eq(id))
                .fetchFirst()
                .toProjection();
    }

    @Override
    public List<TownProjection> findAll() {
        JPAQuery<TownEntity> query = new JPAQuery<>(em);
        return query.from(townEntity).orderBy(townEntity.id.asc()).fetch()
                .stream()
                .map(TownEntity::toProjection)
                .collect(Collectors.toList());
    }
}
