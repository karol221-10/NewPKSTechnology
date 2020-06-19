package pl.kompikownia.pksmanager.ticketmanager.business.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.BuyedTicketProjection;

@Getter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BuyTicketCommand implements Command<BuyedTicketProjection> {

    private String scheduleId;
    private String sourceBusStopId;
    private String destinationBusStopId;
    private String discountId;
}
