package pl.kompikownia.pksmanager.security.infrastructure.service.integration.facebook;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;
import pl.kompikownia.pksmanager.usermanager.business.api.command.UserAccessor;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FacebookConnectionSignup implements ConnectionSignUp {

    private UserAccessor userAccessor;

    @Override
    public String execute(Connection<?> connection) {
        Facebook facebook = (Facebook) connection.getApi();
        String [] fields = { "id", "email",  "first_name", "last_name" };
        val profile = facebook.fetchObject("me", User.class, fields);
        return userAccessor.createNewUser(
                profile.getName(),
                profile.getLastName(),
                profile.getEmail(),
                UUID.randomUUID().toString(),
                profile.getEmail());
    }
}
