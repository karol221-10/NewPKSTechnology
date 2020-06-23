package pl.kompikownia.pksmanager.security.business.internal.api.query;

import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;

import java.util.Optional;

@Getter
public class GetAuthorizedUserDataQuery implements Query<Optional<UserWithLoginData>> {
}
