package pl.kompikownia.pksmanager.ticketmanager.business.projection;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@NoArgsConstructor
public class TicketProjection {
    private Long id;
    private String discountId;
    private String paymentId;
    private String scheduleId;
    private String busStopStartId;
    private String busStopEndId;
    private BigDecimal price;
    private BigDecimal priceAfterDiscount;
    private Boolean paid;
}
