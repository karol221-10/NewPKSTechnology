package pl.kompikownia.pksmanager.busmanager.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.BusEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.repository.port.BusCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.busmanager.infrastructure.entity.QBusEntity.busEntity;

@Repository
@RequiredArgsConstructor
public class BusRepositoryImpl implements BusRepository {

    @PersistenceContext
    private EntityManager em;

    private final BusCrudRepository busCrudRepository;

    @Override
    @Transactional
    public BusProjection save(BusProjection busProjection) {
        val entityToPersist = BusEntity.of(em, busProjection);
        em.persist(entityToPersist);
        em.flush();
        return entityToPersist.toProjection();
    }

    @Override
    @Transactional
    public List<BusProjection> findAll() {
        JPAQuery<BusEntity> query = new JPAQuery<>(em);
        return query.from(busEntity).orderBy(busEntity.id.asc()).fetch()
                .stream()
                .map(BusEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public List<BusProjection> findById(Long busId) {
        JPAQuery<BusEntity> query = new JPAQuery<>(em);
        return query.from(busEntity).where(busEntity.id.eq(busId)).fetch()
                .stream()
                .map(BusEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        JPADeleteClause deleteClause = new JPADeleteClause(em,busEntity);
        deleteClause.where(busEntity.id.eq(id)).execute();
    }

    @Override
    public void deleteAll() { busCrudRepository.deleteAll();}
}
