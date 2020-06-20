package pl.kompikownia.pksmanager.ticketmanager.api.endpoint;


import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;
import pl.kompikownia.pksmanager.ticketmanager.api.dto.DiscountDto;
import pl.kompikownia.pksmanager.ticketmanager.api.request.TicketBuyCompleteRequest;
import pl.kompikownia.pksmanager.ticketmanager.api.request.TicketBuyRequest;
import pl.kompikownia.pksmanager.ticketmanager.api.response.GetAvailableDiscountsResponse;
import pl.kompikownia.pksmanager.ticketmanager.api.response.TicketBuyCompleteResponse;
import pl.kompikownia.pksmanager.ticketmanager.api.response.TicketBuyResponse;
import pl.kompikownia.pksmanager.ticketmanager.api.response.TicketProposalResponse;
import pl.kompikownia.pksmanager.ticketmanager.business.command.BuyTicketCommand;
import pl.kompikownia.pksmanager.ticketmanager.business.command.CompletePaymentCommand;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.query.GetTicketDiscountsQuery;
import pl.kompikownia.pksmanager.ticketmanager.business.query.GetTicketProposalQuery;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TicketEndpoint {

    private QueryExecutor queryExecutor;

    private CommandExecutor commandExecutor;

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
    @PostMapping(value = "/api/ticket")
    TicketBuyResponse buyTicket(@RequestBody TicketBuyRequest request) {
        val command = BuyTicketCommand.builder()
                .scheduleId(request.getScheduleId())
                .sourceBusStopId(request.getSourceBusStopId())
                .destinationBusStopId(request.getDestinationBusStopId())
                .discountId(request.getDiscountId())
                .build();
        val result = commandExecutor.execute(command);

        return TicketBuyResponse.builder()
                .status(result.getStatus())
                .redirectUrl(result.getRedirectUrl())
                .paymentId(result.getPaymentId())
                .payerId(result.getPayerId())
                .build();
    }

    @AnonymousAccess
    @PostMapping(value = "/api/ticket/complete")
    TicketBuyCompleteResponse buyTicketComplete(@RequestBody TicketBuyCompleteRequest ticketBuyCompleteRequest) {
        val result = commandExecutor.execute(CompletePaymentCommand.builder()
                                .payerId(ticketBuyCompleteRequest.getPayerId())
                                .paymentId(ticketBuyCompleteRequest.getPaymentId())
                                .build());
        return new TicketBuyCompleteResponse(result.getQrCode());
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
