package pl.kompikownia.pksmanager.schedulemanager.business.api.response;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
public class BusStop {
    private String id;
    private String scheduleId;
    private String townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private Double price;
    private Double distanceFromPrev;
}
