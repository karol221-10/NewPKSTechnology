package pl.kompikownia.pksmanager.usermanager.business.projection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WorkerData {
    private Long userId;
    private Long workerId;
    private String driverLicenseNumber;
    private String pesel;
    private String personIdNumber;
}
