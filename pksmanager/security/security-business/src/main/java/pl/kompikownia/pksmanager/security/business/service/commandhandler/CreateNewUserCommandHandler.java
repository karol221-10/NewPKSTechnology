package pl.kompikownia.pksmanager.security.business.service.commandhandler;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.command.CreateNewUserCommand;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserProjection;
import pl.kompikownia.pksmanager.security.business.projection.NewUserData;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;

@Handler
@AllArgsConstructor
public class CreateNewUserCommandHandler extends CommandHandler<UserProjection, CreateNewUserCommand> {

    private UserRepository userAuthenticationRepository;

    @Override
    public UserProjection handle(CreateNewUserCommand command) {
        return userAuthenticationRepository.putNewUser(NewUserData.builder()
            .username(command.getUsername())
            .password(command.getPassword())
            .rolesId(command.getRolesId())
            .build());
    }
}
