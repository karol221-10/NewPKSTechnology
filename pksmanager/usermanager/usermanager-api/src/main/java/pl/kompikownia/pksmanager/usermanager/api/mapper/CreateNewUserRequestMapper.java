package pl.kompikownia.pksmanager.usermanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.usermanager.api.request.CreateNewUserRequest;
import pl.kompikownia.pksmanager.usermanager.business.command.CreateUserCommand;

@UtilityClass
public class CreateNewUserRequestMapper {

    public static CreateUserCommand map(CreateNewUserRequest request) {
        return CreateUserCommand.builder()
                .email(request.getEmail())
                .login(request.getLogin())
                .password(request.getPassword())
                .name(request.getName())
                .surname(request.getSurname())
                .build();
    }
}
