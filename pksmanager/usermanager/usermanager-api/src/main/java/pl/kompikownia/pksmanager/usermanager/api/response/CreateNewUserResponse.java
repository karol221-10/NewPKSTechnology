package pl.kompikownia.pksmanager.usermanager.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateNewUserResponse {
    private Long id;
    private String securityUserId;
    private String name;
    private String surname;
    private String email;
}
