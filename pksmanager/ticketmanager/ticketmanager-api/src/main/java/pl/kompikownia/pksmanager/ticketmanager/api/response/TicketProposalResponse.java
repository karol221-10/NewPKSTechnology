package pl.kompikownia.pksmanager.ticketmanager.api.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
public class TicketProposalResponse {
    private String sourceTownId;
    private String destinationTownId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private Long totalTimeSeconds;
    private Long totalDistance;
    private Integer numberOfStations;
    private String price;
}
