package pl.kompikownia.pksmanager.security.business.service.queryhandler;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.context.ContextHolder;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;
import pl.kompikownia.pksmanager.security.business.internal.api.query.GetAuthorizedUserDataQuery;

import java.util.Optional;

@AllArgsConstructor
@Handler
public class GetAuthorizedUserDataQueryHandler extends QueryHandler<Optional<UserWithLoginData>, GetAuthorizedUserDataQuery> {

    private final ContextHolder contextHolder;

    @Override
    public Optional<UserWithLoginData> handle(GetAuthorizedUserDataQuery query) {
        return contextHolder.getAuthorizedUser();
    }
}
