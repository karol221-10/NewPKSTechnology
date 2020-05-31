package pl.kompikownia.pksmanager.busmanager.infrastructure.repository.jpa;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.FuelProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.FuelRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.BusEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.FuelEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.QFuelEntity;
import pl.kompikownia.pksmanager.busmanager.infrastructure.repository.port.FuelCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class FuelRepositoryImpl  implements FuelRepository {

    @PersistenceContext
   private EntityManager em;

    private final FuelCrudRepository fuelCrudRepository;

    @Override
    @Transactional
    public FuelProjection save(FuelProjection fuelProjection) {

        val entityToPersist = FuelEntity.of(em,fuelProjection);
        val parentEntity = em.getReference(BusEntity.class,fuelProjection.getBusId());
        val parentProjection = em.getReference(BusProjection.class, fuelProjection.getBusId());

        parentEntity.getFuelEntity().add(entityToPersist);
        parentProjection.getFuelProjections().add(fuelProjection);

        em.merge(entityToPersist);
        em.merge(parentEntity);
        em.flush();
        return entityToPersist.toProjection();
    }

    @Override
    public List<FuelProjection> findAll() {
        JPAQuery<FuelEntity> query = new JPAQuery<>(em);
        return query.from(QFuelEntity.fuelEntity).orderBy(QFuelEntity.fuelEntity.id.asc())
                .fetch()
                .stream()
                .map(FuelEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public List<FuelProjection> findById(Long id) {
        JPAQuery<FuelEntity> query = new JPAQuery<>(em);
        return query.from(QFuelEntity.fuelEntity).orderBy(QFuelEntity.fuelEntity.id.asc())
                .where(QFuelEntity.fuelEntity.id.eq(id))
                .fetch()
                .stream()
                .map(FuelEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        JPADeleteClause deleteClause = new JPADeleteClause(em, QFuelEntity.fuelEntity);
        deleteClause.where(QFuelEntity.fuelEntity.id.eq(id)).execute();

    }

    @Override
    public void deleteAll() {
        fuelCrudRepository.deleteAll();
    }
}
