package pl.kompikownia.pksmanager.security.business.context;

import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;

import java.util.Optional;

public interface ContextHolder {
    Optional<UserWithLoginData> getAuthorizedUser();
}
