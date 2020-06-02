package pl.kompikownia.pksmanager.busmanager.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.busmanager.entity.UserEntity;

public interface UserRepositoryJPA extends CrudRepository<UserEntity, Long> {
}
