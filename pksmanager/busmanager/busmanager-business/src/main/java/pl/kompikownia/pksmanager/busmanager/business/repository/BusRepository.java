package pl.kompikownia.pksmanager.busmanager.business.repository;

import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.SimpleBusProjection;

import java.util.List;

public interface BusRepository {

    SimpleBusProjection save(SimpleBusProjection busProjection);

    List<BusProjection> findAll();

    List<BusProjection> findById(Long busId);

    void deleteById(Long id);

    void deleteAll();
}
