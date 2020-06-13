package pl.kompikownia.pksmanager.security.api.endpoint;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.security.api.request.LoginRequest;
import pl.kompikownia.pksmanager.security.api.response.LoginResponse;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;
import pl.kompikownia.pksmanager.security.business.query.GetTokenForUserQuery;

@RestController()
@AllArgsConstructor
public class LoginEndpoint {

    private final QueryExecutor queryExecutor;

    @PostMapping("/api/login")
    @AnonymousAccess
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
        val token = queryExecutor.execute(GetTokenForUserQuery.builder()
                    .username(loginRequest.getLogin())
                    .password(loginRequest.getPassword())
                    .build());
        return LoginResponse.of(token);
    }
}
