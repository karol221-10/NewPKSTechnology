package pl.kompikownia.pksmanager.busmanager.infrastructure.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.UserEntity;

public interface UserRepositoryJPA extends CrudRepository<UserEntity, Long> {
}
