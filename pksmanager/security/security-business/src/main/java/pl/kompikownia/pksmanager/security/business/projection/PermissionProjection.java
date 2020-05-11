package pl.kompikownia.pksmanager.security.business.projection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PermissionProjection {
    private String permissionName;
}
