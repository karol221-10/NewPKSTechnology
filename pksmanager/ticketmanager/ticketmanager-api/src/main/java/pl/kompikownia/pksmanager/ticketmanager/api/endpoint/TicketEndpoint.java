package pl.kompikownia.pksmanager.ticketmanager.api.endpoint;


import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;
import pl.kompikownia.pksmanager.ticketmanager.api.dto.DiscountDto;
import pl.kompikownia.pksmanager.ticketmanager.api.response.GetAvailableDiscountsResponse;
import pl.kompikownia.pksmanager.ticketmanager.api.response.TicketProposalResponse;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.query.GetTicketDiscountsQuery;
import pl.kompikownia.pksmanager.ticketmanager.business.query.GetTicketProposalQuery;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TicketEndpoint {

    private QueryExecutor queryExecutor;

    @AnonymousAccess
    @GetMapping(value = "/api/ticket", params = {"scheduleId", "sourceBusStopId", "destinationBusStopId"})
    TicketProposalResponse getTicketProposal(@RequestParam String scheduleId,
                                             @RequestParam String sourceBusStopId,
                                             @RequestParam String destinationBusStopId) {
        val ticketProposal = queryExecutor.execute(GetTicketProposalQuery.builder()
                                            .scheduleId(scheduleId)
                                            .sourceBusStopId(sourceBusStopId)
                                            .destinationBusStopId(destinationBusStopId)
                                            .build());
        return TicketProposalResponse.builder()
                .arrivalDate(ticketProposal.getArrivalDate())
                .departureDate(ticketProposal.getDepartureDate())
                .destinationTownId(ticketProposal.getDestinationTownId())
                .numberOfStations(ticketProposal.getNumberOfStations())
                .price(ticketProposal.getPrice().toString())
                .sourceTownId(ticketProposal.getSourceTownId())
                .totalDistance(ticketProposal.getTotalDistance())
                .totalTimeSeconds(ticketProposal.getTotalTimeSeconds())
                .build();
    }

    @AnonymousAccess
    @GetMapping(value = "/api/discount")
    GetAvailableDiscountsResponse getAvailableDiscountsResponse() {
        val result = queryExecutor.execute(new GetTicketDiscountsQuery());
        val toReturn = result.stream()
                .map(this::map)
                .collect(Collectors.toList());
        return GetAvailableDiscountsResponse.of(toReturn);
    }

    private DiscountDto map(DiscountProjection discountProjection) {
        return DiscountDto.builder()
                .discountId(discountProjection.getId())
                .discountName(discountProjection.getName())
                .discountValue(discountProjection.getValue())
                .build();
    }

}
