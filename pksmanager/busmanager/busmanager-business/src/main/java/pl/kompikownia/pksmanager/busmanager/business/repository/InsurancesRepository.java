package pl.kompikownia.pksmanager.busmanager.business.repository;

import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;

import java.util.List;

public interface InsurancesRepository {

    InsurancesProjection save(InsurancesProjection insurancesProjection);

    InsurancesProjection saveById(InsurancesProjection insurancesProjection,Long id);

    List<InsurancesProjection> findAll();

    List<InsurancesProjection> findAllByBusId(Long id);

    void deleteById(Long id);

    void deleteAll();

    void deleteByBusId(Long id);
}
