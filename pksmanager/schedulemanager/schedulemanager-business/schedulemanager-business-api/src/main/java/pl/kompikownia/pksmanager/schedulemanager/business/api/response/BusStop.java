package pl.kompikownia.pksmanager.schedulemanager.business.api.response;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
public class BusStop {
    private Long id;
    private Long scheduleId;
    private Long townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
