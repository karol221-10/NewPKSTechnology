package pl.kompikownia.pksmanager.usermanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.usermanager.api.request.CreateNewWorkerRequest;
import pl.kompikownia.pksmanager.usermanager.business.command.CreateWorkerCommand;

@UtilityClass
public class CreateNewWorkerRequestMapper {

    public static CreateWorkerCommand map(CreateNewWorkerRequest request) {
        return CreateWorkerCommand.builder()
                .login(request.getLogin())
                .password(request.getPassword())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .driverLicenseNumber(request.getDriverLicenseNumber())
                .pesel(request.getPesel())
                .personIdNumber(request.getPersonIdNumber())
                .build();
    }
}
