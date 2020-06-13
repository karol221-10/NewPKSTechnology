package pl.kompikownia.pksmanager.busmanager.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.SimpleBusProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.BusEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.QBusEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.repository.port.BusCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BusRepositoryImpl implements BusRepository {

    @PersistenceContext
    private EntityManager em;

    private final BusCrudRepository busCrudRepository;

    @Override
    @Transactional
    public SimpleBusProjection save(SimpleBusProjection busProjection) {
        val entityToPersist = BusEntity.of(em, busProjection);
        em.persist(entityToPersist);
        em.flush();
        return entityToPersist.toSimpleProjection();
    }

    @Override
    @Transactional
    public List<BusProjection> findAll() {
        JPAQuery<BusEntity> query = new JPAQuery<>(em);
        return query.from(QBusEntity.busEntity).orderBy(QBusEntity.busEntity.id.asc()).fetch()
                .stream()
                .map(BusEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public List<BusProjection> findById(Long busId) {
        JPAQuery<BusEntity> query = new JPAQuery<>(em);
        return query.from(QBusEntity.busEntity).where(QBusEntity.busEntity.id.eq(busId)).fetch()
                .stream()
                .map(BusEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        JPADeleteClause deleteClause = new JPADeleteClause(em, QBusEntity.busEntity);
        deleteClause.where(QBusEntity.busEntity.id.eq(id)).execute();
    }

    @Override
    public void deleteAll() { busCrudRepository.deleteAll();}
}
