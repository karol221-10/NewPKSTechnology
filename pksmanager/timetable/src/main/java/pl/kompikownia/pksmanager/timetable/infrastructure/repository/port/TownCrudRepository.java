package pl.kompikownia.pksmanager.timetable.infrastructure.repository.port;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;

public interface TownCrudRepository extends CrudRepository<TownEntity,Long> {
}
