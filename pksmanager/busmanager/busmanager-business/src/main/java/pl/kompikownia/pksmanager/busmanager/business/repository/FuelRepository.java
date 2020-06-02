package pl.kompikownia.pksmanager.busmanager.business.repository;

import pl.kompikownia.pksmanager.busmanager.business.projection.FuelProjection;

import java.util.List;

public interface FuelRepository {

    FuelProjection save(FuelProjection fuelProjection);

    List<FuelProjection> findAll();

    List<FuelProjection> findById(Long id);

    void deleteById(Long id);

    void deleteAll();

}
