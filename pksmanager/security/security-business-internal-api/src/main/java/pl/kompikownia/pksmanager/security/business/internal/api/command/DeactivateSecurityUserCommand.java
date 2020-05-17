package pl.kompikownia.pksmanager.security.business.internal.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Command;

@Getter
@AllArgsConstructor(staticName = "of")
public class DeactivateSecurityUserCommand implements Command<Void> {

    private String userId;
}
