package pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.TownEntity;

public interface TownCrudRepository extends CrudRepository<TownEntity,Long> {
}
