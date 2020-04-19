package pl.kompikownia.pksmanager.timetable.business.projection;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
public class BusStopProjection {
    private Long id;
    private Long scheduleId;
    private Long townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
