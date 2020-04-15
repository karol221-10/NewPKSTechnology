package pl.kompikownia.pksmanager.timetable.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import pl.kompikownia.pksmanager.timetable.infrastructure.entity.BusStopEntity;

import java.util.List;

@ToString
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class ScheduleView {

    private Long id;
    private Long busId;
    private Long workerId;
    private boolean isActive;
    private float price;
    private List<BusStopEntity> busStopEntities;
}
