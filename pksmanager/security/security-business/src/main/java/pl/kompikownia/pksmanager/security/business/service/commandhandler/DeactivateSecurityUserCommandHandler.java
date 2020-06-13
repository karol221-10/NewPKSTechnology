package pl.kompikownia.pksmanager.security.business.service.commandhandler;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.command.DeactivateSecurityUserCommand;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;

@Handler
@AllArgsConstructor
public class DeactivateSecurityUserCommandHandler extends CommandHandler<Void, DeactivateSecurityUserCommand> {

    private UserRepository userAuthenticationRepository;

    @Override
    public Void handle(DeactivateSecurityUserCommand command) {
        userAuthenticationRepository.deactivateUser(command.getUserId());
        return null;
    }
}
