package pl.kompikownia.pksmanager.schedulemanager.business.application.mapper;

import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.ScheduleProjection;

import java.util.stream.Collectors;

public class ScheduleMapper {

    public static Schedule map(ScheduleProjection projection) {
        return Schedule.builder()
                .id(projection.getId())
                .busId(projection.getBusId())
                .workerId(projection.getWorkerId())
                .isActive(projection.isActive())
                .price(projection.getPrice())
                .busStops(projection.getBusStops().stream()
                    .map(BusStopMapper::map)
                    .collect(Collectors.toList()))
                .build();
    }
}
