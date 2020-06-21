package pl.kompikownia.pksmanager.schedulemanager.business.internal.api.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
public class BusStop {
    private String id;
    private String townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private BigDecimal price;
    private BigDecimal distanceFromPrev;
}
