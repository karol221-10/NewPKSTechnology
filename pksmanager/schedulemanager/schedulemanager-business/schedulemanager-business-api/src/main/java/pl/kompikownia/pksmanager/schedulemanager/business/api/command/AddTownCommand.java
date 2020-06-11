package pl.kompikownia.pksmanager.schedulemanager.business.api.command;

import lombok.*;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Town;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddTownCommand implements Command<Town> {
    private Long id;
    private String townName;
}
