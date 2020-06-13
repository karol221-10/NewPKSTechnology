package pl.kompikownia.pksmanager.usermanager.business.projection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatedUserProjection {
    private Long id;
    private String securityUserId;
    private String name;
    private String surname;
    private String email;
}
