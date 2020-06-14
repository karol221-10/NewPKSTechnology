package pl.kompikownia.pksmanager.security.infrastructure.repository.port;

import pl.kompikownia.pksmanager.security.infrastructure.model.UserDetailsModel;

public interface UserAuthenticationRepository {
    UserDetailsModel findByUsername(String username);
    UserDetailsModel findById(String id);
}
