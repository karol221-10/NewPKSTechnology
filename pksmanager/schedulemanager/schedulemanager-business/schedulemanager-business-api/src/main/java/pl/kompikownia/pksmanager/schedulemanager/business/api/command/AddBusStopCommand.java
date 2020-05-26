package pl.kompikownia.pksmanager.schedulemanager.business.api.command;

import lombok.*;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddBusStopCommand implements Command<BusStop> {

    private String townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private String scheduleId;
}
