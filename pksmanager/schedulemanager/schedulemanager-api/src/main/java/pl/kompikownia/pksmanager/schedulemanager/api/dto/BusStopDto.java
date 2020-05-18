package pl.kompikownia.pksmanager.schedulemanager.api.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
public class BusStopDto {
    private Long id;
    private Long scheduleId;
    private Long townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
