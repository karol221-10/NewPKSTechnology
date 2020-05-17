package pl.kompikownia.pksmanager.security.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.security.api.response.LoginResponse;


@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor(staticName = "of")
public class LoginRequest {

    private String login;
    private String password;
}
