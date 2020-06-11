package pl.kompikownia.pksmanager.schedulemanager.api.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
public class BusStopDto {
    private String id;
    private String scheduleId;
    private String townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private Double distanceFromPrev;
    private Double price;
}
