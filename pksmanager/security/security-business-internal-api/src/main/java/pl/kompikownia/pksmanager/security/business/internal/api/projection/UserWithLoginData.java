package pl.kompikownia.pksmanager.security.business.internal.api.projection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserWithLoginData {
    private String id;
    private String login;
}
