package pl.kompikownia.pksmanager.usermanager.infrastructure.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.FuelEntity;

public interface FuelCrudRepository extends CrudRepository<FuelEntity,Long> {
}
