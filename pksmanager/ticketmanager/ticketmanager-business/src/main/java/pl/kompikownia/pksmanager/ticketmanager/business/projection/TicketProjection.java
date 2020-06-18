package pl.kompikownia.pksmanager.ticketmanager.business.projection;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@NoArgsConstructor
public class TicketProjection {
    private Long id;
    private String scheduleId;
    private String busStopStartId;
    private String busStopEndId;
    private BigDecimal price;
}
