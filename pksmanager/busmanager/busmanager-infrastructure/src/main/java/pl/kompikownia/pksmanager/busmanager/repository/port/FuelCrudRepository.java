package pl.kompikownia.pksmanager.busmanager.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.busmanager.entity.FuelEntity;

public interface FuelCrudRepository extends CrudRepository<FuelEntity,Long> {
}
