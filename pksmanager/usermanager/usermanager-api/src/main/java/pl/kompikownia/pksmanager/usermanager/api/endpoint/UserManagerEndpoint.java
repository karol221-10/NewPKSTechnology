package pl.kompikownia.pksmanager.usermanager.api.endpoint;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;
import pl.kompikownia.pksmanager.usermanager.api.mapper.CreateNewUserRequestMapper;
import pl.kompikownia.pksmanager.usermanager.api.mapper.CreateNewUserResponseMapper;
import pl.kompikownia.pksmanager.usermanager.api.request.CreateNewUserRequest;
import pl.kompikownia.pksmanager.usermanager.api.response.CreateNewUserResponse;

@RestController
@AllArgsConstructor
public class UserManagerEndpoint {

    private CommandExecutor commandExecutor;

    @PostMapping("/api/user")
    @AnonymousAccess
    public CreateNewUserResponse createNewUser(@RequestBody CreateNewUserRequest request) {
        val result = commandExecutor.execute(CreateNewUserRequestMapper.map(request));
        return CreateNewUserResponseMapper.map(result);
    }
}
