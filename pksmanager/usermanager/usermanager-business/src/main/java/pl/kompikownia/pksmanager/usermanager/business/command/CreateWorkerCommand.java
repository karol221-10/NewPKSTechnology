package pl.kompikownia.pksmanager.usermanager.business.command;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedWorkerProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.SavedWorkerInDbProjection;

@Builder
@Getter
public class CreateWorkerCommand implements Command<CreatedWorkerProjection> {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String driverLicenseNumber;
    private String pesel;
    private String personIdNumber;
}
