package pl.kompikownia.pksmanager.security.business.service.queryhandler;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;
import pl.kompikownia.pksmanager.security.business.internal.api.query.GetUsersByIdsQuery;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;

import java.util.List;


@Handler
@AllArgsConstructor
public class GetUsersByIdsQueryHandler extends QueryHandler<List<UserWithLoginData>, GetUsersByIdsQuery> {

    private UserRepository userRepository;

    @Override
    public List<UserWithLoginData> handle(GetUsersByIdsQuery query) {
        return userRepository.getUsersByIds(query.getIds());
    }
}
