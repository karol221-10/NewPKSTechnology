package pl.kompikownia.pksmanager.ticketmanager.business.query;

import lombok.*;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProposal;

@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GetTicketProposalQuery implements Query<TicketProposal> {
    private String scheduleId;
    private String sourceBusStopId;
    private String destinationBusStopId;
}
