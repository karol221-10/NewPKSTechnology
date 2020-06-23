package pl.kompikownia.pksmanager.ticketmanager.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TicketBuyRequest {
    private String scheduleId;
    private String sourceBusStopId;
    private String destinationBusStopId;
    private String discountId;
}
