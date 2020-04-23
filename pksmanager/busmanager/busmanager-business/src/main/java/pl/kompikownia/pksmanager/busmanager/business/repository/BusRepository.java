package pl.kompikownia.pksmanager.busmanager.business.repository;

import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;

import java.util.List;

public interface BusRepository {

    BusProjection save(BusProjection busProjection);

    List<BusProjection> findAll();

    List<BusProjection> findById(Long busId);

    void deleteById(Long id);

    void deleteAll();
}
