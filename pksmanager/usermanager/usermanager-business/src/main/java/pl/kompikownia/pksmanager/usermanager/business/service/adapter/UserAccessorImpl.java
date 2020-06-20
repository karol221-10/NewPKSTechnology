package pl.kompikownia.pksmanager.usermanager.business.service.adapter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.security.business.internal.api.command.CreateNewUserCommand;
import pl.kompikownia.pksmanager.usermanager.business.api.command.UserAccessor;
import pl.kompikownia.pksmanager.usermanager.business.command.CreateUserCommand;
import pl.kompikownia.pksmanager.usermanager.business.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserAccessorImpl implements UserAccessor {

    @Value("${pl.kompikownia.pksmanager.defaultRoleName}")
    private String defaultRoleName;

    private final CommandExecutor commandExecutor;

    private final UserRepository userRepository;

    @Override
    public String createNewUser(String name, String surname, String login, String password, String email) {
        val newUserCommand = CreateUserCommand.builder()
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .email(email)
                .roleName(defaultRoleName)
                .build();
        val result = commandExecutor.execute(newUserCommand);
        return result.getSecurityUserId();
    }

    @Override
    public Integer getUserCount() {
        return userRepository.getUserList().size();
    }

    @Override
    public Integer getWorkerCount() {
        return userRepository.getWorkersList().size();
    }
}
