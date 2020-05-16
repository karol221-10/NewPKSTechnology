package pl.kompikownia.pksmanager.usermanager.business.command;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedUserProjection;


@Builder
@Getter
public class CreateUserCommand implements Command<CreatedUserProjection> {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String roleName;
}
