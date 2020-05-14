package pl.kompikownia.pksmanager.usermanager.business.service.command;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.command.CreateNewUserCommand;
import pl.kompikownia.pksmanager.security.business.internal.api.query.GetRoleIdByNameQuery;
import pl.kompikownia.pksmanager.usermanager.business.command.CreateUserCommand;
import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedUserProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.repository.UserRepository;

import java.util.List;

@Handler
public class CreateUserCommandHandler extends CommandHandler<CreatedUserProjection, CreateUserCommand> {

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    private UserRepository userRepository;

    public CreateUserCommandHandler(@Lazy CommandExecutor commandExecutor,@Lazy QueryExecutor queryExecutor, UserRepository userRepository) {
        this.commandExecutor = commandExecutor;
        this.queryExecutor = queryExecutor;
        this.userRepository = userRepository;
    }

    @Value("${pl.kompikownia.defaultRoleName}")
    private String defaultRoleName;

    @Override
    public CreatedUserProjection handle(CreateUserCommand command) {
        val roleId = queryExecutor.execute(GetRoleIdByNameQuery.of(defaultRoleName));
        CreateNewUserCommand createUserCommand = CreateNewUserCommand.builder()
                .username(command.getLogin())
                .password(command.getPassword())
                .rolesId(List.of(roleId.toString()))
                .build();
        val userProjection = commandExecutor.execute(createUserCommand);
        val userData = UserData.builder()
                .email(command.getEmail())
                .name(command.getName())
                .surname(command.getSurname())
                .securedId(userProjection.getId())
                .build();
        return userRepository.saveUser(userData);
    }
}
