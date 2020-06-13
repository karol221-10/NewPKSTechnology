package pl.kompikownia.pksmanager.security.infrastructure.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.kompikownia.pksmanager.security.infrastructure.entity.SecurityUserEntity;

public interface UserCrudRepository extends CrudRepository<SecurityUserEntity, Long> {
}
