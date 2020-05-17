package pl.kompikownia.pksmanager.security.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class LoginResponse {

    private String token;
}
