package pl.kompikownia.pksmanager.security.business.projection;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(builderClassName = "builder")
public class UserWithPermissionProjection {

    private String username;
    private List<String> permissionNames;
}
