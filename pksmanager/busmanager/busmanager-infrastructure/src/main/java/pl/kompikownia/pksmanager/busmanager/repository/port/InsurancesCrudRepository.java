package pl.kompikownia.pksmanager.busmanager.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.busmanager.entity.InsurancesEntity;

public interface InsurancesCrudRepository extends CrudRepository<InsurancesEntity,Long> {
}
