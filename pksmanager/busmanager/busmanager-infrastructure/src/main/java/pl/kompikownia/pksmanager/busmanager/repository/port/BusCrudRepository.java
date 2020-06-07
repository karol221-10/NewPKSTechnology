package pl.kompikownia.pksmanager.busmanager.repository.port;


import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.BusEntity;

public interface BusCrudRepository extends CrudRepository<BusEntity,Long> {
}
