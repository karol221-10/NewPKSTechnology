package pl.kompikownia.pksmanager.schedulemanager.business.api.command;

import lombok.*;
import pl.kompikownia.pksmanager.cqrs.domain.Command;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteBusStopCommand implements Command<Void> {

    private String busStopId;
}
