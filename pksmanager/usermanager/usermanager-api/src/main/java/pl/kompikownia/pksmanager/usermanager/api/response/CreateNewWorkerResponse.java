package pl.kompikownia.pksmanager.usermanager.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreateNewWorkerResponse {
    private String id;
    private String securityUserId;
    private String name;
    private String surname;
    private String email;
    private String driverLicenseNumber;
    private String pesel;
    private String personIdNumber;
}
