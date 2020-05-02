package pl.kompikownia.pksmanager.security.business.projection;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoleProjection {

    private String roleName;
    private List<PermissionProjection> associatedPermissions;
}
