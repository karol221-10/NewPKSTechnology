package pl.kompikownia.pksmanager.security.business.internal.api.command;

import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserProjection;

import java.util.List;

@Getter
public class CreateNewUserCommand implements Command<UserProjection> {

    private String username;
    private String password;
    private List<String> rolesId;
}
