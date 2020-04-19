package pl.kompikownia.pksmanager.timetable.infrastructure.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.BusStopEntity;


public interface BusStopCrudRepository extends CrudRepository<BusStopEntity, Long> {
}
