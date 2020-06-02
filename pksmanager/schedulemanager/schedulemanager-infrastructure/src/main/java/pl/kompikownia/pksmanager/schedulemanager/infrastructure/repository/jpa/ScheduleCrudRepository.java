package pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository.jpa;


import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity.ScheduleEntity;

public interface ScheduleCrudRepository extends CrudRepository<ScheduleEntity, Long> {
}
