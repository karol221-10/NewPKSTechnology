package pl.kompikownia.pksmanager.ticketmanager.business.projection;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketProposal {
    private String sourceTownId;
    private String destinationTownId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private Long totalTimeSeconds;
    private Long totalDistance;
    private Integer numberOfStations;
    private BigDecimal price;
}
