package pl.kompikownia.pksmanager.security.infrastructure.context;

import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.kompikownia.pksmanager.security.business.context.ContextHolder;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;
import pl.kompikownia.pksmanager.security.infrastructure.model.UserDetailsModel;

import java.util.Optional;

@Service
public class ContextHolderImpl implements ContextHolder {

    @Override
    public Optional<UserWithLoginData> getAuthorizedUser() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }
        val user = (UserDetailsModel)authentication.getPrincipal();
        return Optional.of(
                UserWithLoginData.builder()
                .id(user.getUserId())
                .login(user.getUsername())
                .build()
        );
    }
}
