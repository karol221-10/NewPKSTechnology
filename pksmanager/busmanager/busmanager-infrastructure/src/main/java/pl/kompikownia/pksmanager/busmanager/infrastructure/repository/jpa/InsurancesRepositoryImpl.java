package pl.kompikownia.pksmanager.busmanager.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.BusEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.InsurancesEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.repository.port.InsurancesCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.busmanager.infrastructure.entity.QInsurancesEntity.insurancesEntity;

@Repository
@RequiredArgsConstructor
public class InsurancesRepositoryImpl implements InsurancesRepository {

    @PersistenceContext
    private EntityManager em;

    private final InsurancesCrudRepository insurancesCrudRepository;


    @Override
    @Transactional
    public InsurancesProjection save(InsurancesProjection insurancesProjection) {
        val entityToPersist = InsurancesEntity.of(em,insurancesProjection);
        val parentEntity = em.getReference(BusEntity.class, insurancesProjection.getBusId());
        val parentProjection = em.getReference(BusProjection.class, insurancesProjection.getBusId());
        parentEntity.getInsurancesEntities().add(entityToPersist);
        parentProjection.getInsurancesProjections().add(insurancesProjection);
        em.merge(entityToPersist);
        em.merge(parentEntity);
        em.flush();
        return entityToPersist.toProjection();
    }

    @Override
    public InsurancesProjection saveById(InsurancesProjection insurancesProjection, Long id) {
        val entityToPersist = InsurancesEntity.of(em,insurancesProjection);
        val parentEntity = em.getReference(BusEntity.class, id);
        val parentProjection = em.getReference(BusProjection.class, id);
        parentEntity.getInsurancesEntities().add(entityToPersist);
        parentProjection.getInsurancesProjections().add(insurancesProjection);
        em.merge(entityToPersist);
        em.merge(parentEntity);
        em.flush();
        return entityToPersist.toProjection();
    }


    @Override
    public List<InsurancesProjection> findAll() {
        JPAQuery<InsurancesEntity> query = new JPAQuery<>(em);
        return query.from(insurancesEntity).orderBy(insurancesEntity.id.asc()).fetch()
                .stream()
                .map(InsurancesEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public List<InsurancesProjection> findAllByBusId(Long id) {
        JPAQuery<InsurancesEntity> query = new JPAQuery<>(em);
        return query.from(insurancesEntity).orderBy(insurancesEntity.id.asc())
                .where(insurancesEntity.bus.id.eq(id))
                .fetch()
                .stream()
                .map(InsurancesEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        JPADeleteClause deleteClause = new JPADeleteClause(em,insurancesEntity);
        deleteClause.where(insurancesEntity.id.eq(id)).execute();
    }

    @Override
    public void deleteAll() { insurancesCrudRepository.deleteAll(); }

    @Override
    public void deleteByBusId(Long id) {
        JPADeleteClause deleteClause = new JPADeleteClause(em,insurancesEntity);
        deleteClause.where(insurancesEntity.bus.id.eq(id)).execute();
    }
}
