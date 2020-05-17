package pl.kompikownia.pksmanager.usermanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.usermanager.api.response.CreateNewWorkerResponse;
import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedWorkerProjection;

@UtilityClass
public class CreateNewWorkerResponseMapper {

    public static CreateNewWorkerResponse map(CreatedWorkerProjection projection) {
        return CreateNewWorkerResponse.builder()
                .id(projection.getId())
                .securityUserId(projection.getSecurityUserId())
                .name(projection.getName())
                .surname(projection.getSurname())
                .email(projection.getEmail())
                .driverLicenseNumber(projection.getDriverLicenseNumber())
                .personIdNumber(projection.getPersonIdNumber())
                .pesel(projection.getPesel())
                .build();
    }
}
