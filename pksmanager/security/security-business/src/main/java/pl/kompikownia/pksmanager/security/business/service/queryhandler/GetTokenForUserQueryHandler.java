package pl.kompikownia.pksmanager.security.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.query.GetTokenForUserQuery;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;

@Handler
@AllArgsConstructor
public class GetTokenForUserQueryHandler extends QueryHandler<String, GetTokenForUserQuery> {

    private TokenProvider tokenProvider;
    private UserRepository userRepository;

    @Override
    public String handle(GetTokenForUserQuery query) {
        val userId = userRepository.getUserByUsernameAndPassword(query.getUsername(), query.getPassword());
        val userWithPermissions = userRepository.getUserWithPermissionsById(userId);
        return tokenProvider.generateToken(userWithPermissions.getUsername(), userWithPermissions.getPermissionNames());
    }
}
