package pl.kompikownia.pksmanager.usermanager.infrastructure.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.UserEntity;

public interface UserRepositoryJPA extends CrudRepository<UserEntity, Long> {
}
