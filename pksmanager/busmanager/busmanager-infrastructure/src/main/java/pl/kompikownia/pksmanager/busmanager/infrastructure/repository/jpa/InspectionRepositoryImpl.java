package pl.kompikownia.pksmanager.busmanager.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.InspectionRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.BusEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.InspectionEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.QInspectionEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.repository.port.InspectionCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InspectionRepositoryImpl implements InspectionRepository {

    @PersistenceContext
    private EntityManager em;

    private final InspectionCrudRepository inspectionCrudRepository;

    @Override
    @Transactional
    public InspectionProjection save(InspectionProjection inspectionProjection) {
        val entityToPersist = InspectionEntity.of(em,inspectionProjection);
        val parentEntity = em.getReference(BusEntity.class,inspectionProjection.getBusId());
        parentEntity.getInspectionEntity().add(entityToPersist);

        val persistedEntity = em.merge(entityToPersist);
        em.merge(parentEntity);
        em.flush();
        return persistedEntity.toProjection();
    }

    @Override
    public InspectionProjection saveById(InspectionProjection inspectionProjection, Long id) {

        val entityToPersist = InspectionEntity.of(em,inspectionProjection);
        val parentEntity = em.getReference(BusEntity.class,id);
        parentEntity.getInspectionEntity().add(entityToPersist);
        em.merge(entityToPersist);
        em.merge(parentEntity);
        em.flush();
        return entityToPersist.toProjection();
    }

    @Override
    public List<InspectionProjection> findAll() {
        JPAQuery<InspectionEntity> query = new JPAQuery<>(em);
        return query.from(QInspectionEntity.inspectionEntity).orderBy(QInspectionEntity.inspectionEntity.id.asc())
                .fetch()
                .stream()
                .map(InspectionEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public List<InspectionProjection> findAllByBusId(Long id) {

        JPAQuery<InspectionEntity> query = new JPAQuery<>(em);
        return query.from(QInspectionEntity.inspectionEntity).orderBy(QInspectionEntity.inspectionEntity.id.asc())
                .where(QInspectionEntity.inspectionEntity.bus.id.eq(id))
                .fetch()
                .stream()
                .map(InspectionEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        JPADeleteClause deleteClause = new JPADeleteClause(em, QInspectionEntity.inspectionEntity);
        deleteClause.where(QInspectionEntity.inspectionEntity.id.eq(id)).execute();
    }

    @Override
    public void deleteAll() { inspectionCrudRepository.deleteAll();}

    @Override
    public void deleteByBusId(Long id) {
        JPADeleteClause deleteClause = new JPADeleteClause(em, QInspectionEntity.inspectionEntity);
        deleteClause.where(QInspectionEntity.inspectionEntity.bus.id.eq(id)).execute();
    }
}
