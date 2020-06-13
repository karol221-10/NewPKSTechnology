package pl.kompikownia.pksmanager.schedulemanager.business.api.command;

import lombok.*;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateBusStopCommand implements Command<BusStop> {

    private String id;
    private String scheduleId;
    private String townId;
    private String price;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
