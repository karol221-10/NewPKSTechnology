package pl.kompikownia.pksmanager.busmanager.infrastructure.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.FuelEntity;

public interface FuelCrudRepository extends CrudRepository<FuelEntity,Long> {
}
