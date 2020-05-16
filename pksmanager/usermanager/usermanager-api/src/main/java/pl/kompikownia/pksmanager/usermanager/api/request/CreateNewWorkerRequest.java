package pl.kompikownia.pksmanager.usermanager.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateNewWorkerRequest {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String driverLicenseNumber;
    private String pesel;
    private String personIdNumber;
}
