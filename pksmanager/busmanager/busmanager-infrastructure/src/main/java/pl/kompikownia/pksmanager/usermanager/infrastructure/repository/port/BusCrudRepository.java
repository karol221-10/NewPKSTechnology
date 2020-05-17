package pl.kompikownia.pksmanager.usermanager.infrastructure.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.BusEntity;

public interface BusCrudRepository extends CrudRepository<BusEntity,Long> {
}
