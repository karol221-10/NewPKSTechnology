package pl.kompikownia.pksmanager.schedulemanager.api.dto;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder(builderClassName = "builder")
@Getter
@ToString
@EqualsAndHashCode
public class NewBusStopDto {

    private Long townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
