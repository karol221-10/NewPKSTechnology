package pl.kompikownia.pksmanager.schedulemanager.business.application.projection;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
@Setter
public class BusStopProjection {
    private String id;
    private String scheduleId;
    private String townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private BigDecimal price;
    private BigDecimal distanceFromPrev;
}
