package pl.kompikownia.pksmanager.schedulemanager.business.api.command;

import lombok.*;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddNewScheduleCommand implements Command<Schedule> {

    private Long busId;
    private Long workerId;
}
