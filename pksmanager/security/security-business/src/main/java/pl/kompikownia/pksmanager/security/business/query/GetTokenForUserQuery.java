package pl.kompikownia.pksmanager.security.business.query;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Query;

@Getter
@Builder
public class GetTokenForUserQuery implements Query<String> {

    private String username;
    private String password;
}
