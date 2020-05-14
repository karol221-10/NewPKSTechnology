package pl.kompikownia.pksmanager.usermanager.business.projection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserData {

    private String name;
    private String surname;
    private String email;
    private String securedId;
}
