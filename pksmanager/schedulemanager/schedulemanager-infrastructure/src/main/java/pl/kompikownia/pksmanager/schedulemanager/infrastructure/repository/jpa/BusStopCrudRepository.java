package pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository.jpa;


import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.BusStopEntity;

public interface BusStopCrudRepository extends CrudRepository<BusStopEntity, Long> {
}
