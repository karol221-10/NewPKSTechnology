package pl.kompikownia.pksmanager.security.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.query.GetTokenForUserQuery;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;

@Handler
@AllArgsConstructor
public class GetTokenForUserQueryHandler extends QueryHandler<String, GetTokenForUserQuery> {

    private TokenProvider tokenProvider;
    private UserRepository userAuthenticationRepository;

    @Override
    public String handle(GetTokenForUserQuery query) {
        val userId = userAuthenticationRepository.getUserByUsernameAndPassword(query.getUsername(), query.getPassword());
        val userWithPermissions = userAuthenticationRepository.getUserWithPermissionsById(userId);
        if(!userAuthenticationRepository.isUserActive(userId.toString())) {
            throw new IllegalArgumentException("User "+ userId+ "is deactivated");
        }
        return tokenProvider.generateToken(userWithPermissions.getUserId(), userWithPermissions.getPermissionNames());
    }
}
