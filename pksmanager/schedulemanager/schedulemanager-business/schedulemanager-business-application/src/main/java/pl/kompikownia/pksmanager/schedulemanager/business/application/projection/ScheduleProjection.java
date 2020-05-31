package pl.kompikownia.pksmanager.schedulemanager.business.application.projection;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Setter
public class ScheduleProjection {

    private Long id;
    private Long busId;
    private Long workerId;
    private boolean isActive;
    private float price;
    private List<BusStopProjection> busStops;
}
