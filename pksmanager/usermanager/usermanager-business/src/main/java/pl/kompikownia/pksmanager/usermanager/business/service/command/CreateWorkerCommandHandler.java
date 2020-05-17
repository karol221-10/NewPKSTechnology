package pl.kompikownia.pksmanager.usermanager.business.service.command;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.usermanager.business.command.CreateUserCommand;
import pl.kompikownia.pksmanager.usermanager.business.command.CreateWorkerCommand;
import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedWorkerProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;
import pl.kompikownia.pksmanager.usermanager.business.repository.UserRepository;

@Handler
public class CreateWorkerCommandHandler extends CommandHandler<CreatedWorkerProjection, CreateWorkerCommand> {

    private CommandExecutor commandExecutor;

    private UserRepository userRepository;

    @Value("${pl.kompikownia.pksmanager.defaultWorkerRoleName}")
    private String defaultWorkerRoleName;

    public CreateWorkerCommandHandler(@Lazy CommandExecutor commandExecutor, UserRepository userRepository) {
        this.commandExecutor = commandExecutor;
        this.userRepository = userRepository;
    }

    @Override
    public CreatedWorkerProjection handle(CreateWorkerCommand command) {
        val createdUserProjection = commandExecutor.execute(getCreateUserCommand(command));
        val savedWorker = userRepository.saveWorker(WorkerData.builder()
            .workerId(createdUserProjection.getId())
            .driverLicenseNumber(command.getDriverLicenseNumber())
            .personIdNumber(command.getPersonIdNumber())
            .pesel(command.getPesel())
            .build());
        return CreatedWorkerProjection.builder()
                .id(savedWorker.getId().toString())
                .securityUserId(createdUserProjection.getSecurityUserId())
                .name(createdUserProjection.getName())
                .surname(createdUserProjection.getSurname())
                .email(createdUserProjection.getEmail())
                .driverLicenseNumber(savedWorker.getDriverLicenseNumber())
                .pesel(savedWorker.getPesel())
                .personIdNumber(savedWorker.getPersonIdNumber())
                .build();
    }

    private CreateUserCommand getCreateUserCommand(CreateWorkerCommand command) {
        return CreateUserCommand.builder()
                .name(command.getName())
                .surname(command.getSurname())
                .roleName(defaultWorkerRoleName)
                .password(command.getPassword())
                .login(command.getLogin())
                .email(command.getEmail())
                .build();
    }
}
