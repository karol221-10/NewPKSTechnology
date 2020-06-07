package pl.kompikownia.pksmanager.schedulemanager.business.api.command;

import lombok.*;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Town;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddTownCommand implements Command<Town> {
    private String townName;
}
