package pl.kompikownia.pksmanager.busmanager.infrastructure.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.InsurancesEntity;

public interface InsurancesCrudRepository extends CrudRepository<InsurancesEntity,Long> {
}
