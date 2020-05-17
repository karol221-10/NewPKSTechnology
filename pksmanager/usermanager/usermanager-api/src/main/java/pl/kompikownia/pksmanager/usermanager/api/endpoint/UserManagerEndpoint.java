package pl.kompikownia.pksmanager.usermanager.api.endpoint;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;
import pl.kompikownia.pksmanager.usermanager.api.mapper.*;
import pl.kompikownia.pksmanager.usermanager.api.request.CreateNewUserRequest;
import pl.kompikownia.pksmanager.usermanager.api.request.CreateNewWorkerRequest;
import pl.kompikownia.pksmanager.usermanager.api.response.CreateNewUserResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.CreateNewWorkerResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.GetUserListResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.GetWorkerListResponse;
import pl.kompikownia.pksmanager.usermanager.business.command.DeactivateUserCommand;
import pl.kompikownia.pksmanager.usermanager.business.query.GetUserListQuery;
import pl.kompikownia.pksmanager.usermanager.business.query.GetWorkerListQuery;

@RestController
@RequiredArgsConstructor
public class UserManagerEndpoint {

    private final CommandExecutor commandExecutor;

    private final QueryExecutor queryExecutor;

    @Value("${pl.kompikownia.defaultRoleName}")
    private String defaultRoleName;

    @PostMapping("/api/users")
    @AnonymousAccess
    public CreateNewUserResponse createNewUser(@RequestBody CreateNewUserRequest request) {
        val result = commandExecutor.execute(CreateNewUserRequestMapper.map(request, defaultRoleName));
        return CreateNewUserResponseMapper.map(result);
    }

    @PostMapping("/api/workers")
    public CreateNewWorkerResponse createNewWorker(@RequestBody CreateNewWorkerRequest request) {
        val result = commandExecutor.execute(CreateNewWorkerRequestMapper.map(request));
        return CreateNewWorkerResponseMapper.map(result);
    }

    @GetMapping("/api/users")
    public GetUserListResponse getUserList() {
        val result = queryExecutor.execute(new GetUserListQuery());
        return GetUserListResponseMapper.map(result);
    }

    @GetMapping("/api/workers")
    public GetWorkerListResponse getWorkerList() {
        val workers = queryExecutor.execute(new GetWorkerListQuery());
        val users = queryExecutor.execute(new GetUserListQuery());
        return GetWorkerListResponseMapper.map(workers, users);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteWorker(@PathVariable String id) {
        commandExecutor.execute(DeactivateUserCommand.of(id));
    }
}
