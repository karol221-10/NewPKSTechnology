package pl.kompikownia.pksmanager.busmanager.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.InspectionEntity;

public interface InspectionCrudRepository extends CrudRepository<InspectionEntity,Long> {
}
