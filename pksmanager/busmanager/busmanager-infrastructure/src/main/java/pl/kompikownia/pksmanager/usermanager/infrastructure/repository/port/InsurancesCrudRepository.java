package pl.kompikownia.pksmanager.usermanager.infrastructure.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.InsurancesEntity;

public interface InsurancesCrudRepository extends CrudRepository<InsurancesEntity,Long> {
}
