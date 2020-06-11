package pl.kompikownia.pksmanager.schedulemanager.api.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Setter
public class ScheduleDto {
    private Long id;
    private Long busId;
    private Long workerId;
    private boolean isActive;
    private float price;
    private List<BusStopDto> busStops;
    private Double totalDistance;
    private Long totalTimeSeconds;
}
