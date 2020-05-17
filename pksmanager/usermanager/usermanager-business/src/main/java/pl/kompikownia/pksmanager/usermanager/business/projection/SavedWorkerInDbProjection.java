package pl.kompikownia.pksmanager.usermanager.business.projection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SavedWorkerInDbProjection {
    private Long id;
    private String driverLicenseNumber;
    private String pesel;
    private String personIdNumber;
}
