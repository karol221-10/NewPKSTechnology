package pl.kompikownia.pksmanager.busmanager.business.repository;

import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;

import java.util.List;

public interface InspectionRepository {

    InspectionProjection save(InspectionProjection inspectionProjection);

    InspectionProjection saveById(InspectionProjection inspectionProjection,Long id);

    List<InspectionProjection> findAll();

    List<InspectionProjection> findAllByBusId(Long id);

    void deleteById(Long id);

    void deleteAll();

    void deleteByBusId(Long id);
}
