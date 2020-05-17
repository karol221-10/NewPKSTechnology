package pl.kompikownia.pksmanager.usermanager.api.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewUserRequest {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
}
