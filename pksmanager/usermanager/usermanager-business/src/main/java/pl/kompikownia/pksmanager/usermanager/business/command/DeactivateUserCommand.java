package pl.kompikownia.pksmanager.usermanager.business.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Command;

@Getter
@AllArgsConstructor(staticName = "of")
public class DeactivateUserCommand implements Command<Void> {

    private String userId;
}
