package pl.kompikownia.pksmanager.usermanager.business.projection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatedWorkerProjection {
    private String id;
    private String securityUserId;
    private String name;
    private String surname;
    private String email;
    private String driverLicenseNumber;
    private String pesel;
    private String personIdNumber;
}
