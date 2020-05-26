package pl.kompikownia.pksmanager.schedulemanager.business.api.command;

import lombok.*;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.SimpleSchedule;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateScheduleCommand implements Command<SimpleSchedule> {

    private String id;
    private String busId;
    private String workerId;
    private boolean isActive;
}
