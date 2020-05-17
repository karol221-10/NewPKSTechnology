package pl.kompikownia.pksmanager.usermanager.business.service.command;

import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.command.DeactivateSecurityUserCommand;
import pl.kompikownia.pksmanager.usermanager.business.command.DeactivateUserCommand;
import pl.kompikownia.pksmanager.usermanager.business.repository.UserRepository;

@Handler
public class DeactivateUserCommandHandler extends CommandHandler<Void, DeactivateUserCommand> {

    private UserRepository userRepository;
    private CommandExecutor commandExecutor;

    public DeactivateUserCommandHandler(UserRepository userRepository,@Lazy CommandExecutor commandExecutor) {
        this.userRepository = userRepository;
        this.commandExecutor = commandExecutor;
    }

    @Override
    public Void handle(DeactivateUserCommand command) {
        commandExecutor.execute(DeactivateSecurityUserCommand.of(command.getUserId()));
        userRepository.deactivateUser(command.getUserId());
        return null;
    }
}
