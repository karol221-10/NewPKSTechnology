package pl.kompikownia.pksmanager.schedulemanager.business.application.projection;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
@Setter
public class BusStopProjection {
    private Long id;
    private Long scheduleId;
    private Long townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}