package pl.kompikownia.pksmanager.security.business.projection;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder(builderClassName = "builder")
@Getter
public class NewUserData {
    private String username;
    private String password;
    private List<String> rolesId;
}
