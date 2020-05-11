package pl.kompikownia.pksmanager.security.infrastructure.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.security.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<UserEntity, Long> {
}
