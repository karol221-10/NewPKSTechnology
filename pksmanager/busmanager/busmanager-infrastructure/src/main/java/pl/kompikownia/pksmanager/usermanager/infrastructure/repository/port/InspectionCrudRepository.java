package pl.kompikownia.pksmanager.usermanager.infrastructure.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.InspectionEntity;

public interface InspectionCrudRepository extends CrudRepository<InspectionEntity,Long> {
}
