package pl.kompikownia.pksmanager.security.business.internal.api.projection;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserProjection {
    private String id;
    private String username;
    private List<String> rolesId;
}
