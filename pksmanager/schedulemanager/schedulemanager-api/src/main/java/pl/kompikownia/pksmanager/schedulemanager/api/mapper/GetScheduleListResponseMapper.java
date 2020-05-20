package pl.kompikownia.pksmanager.schedulemanager.api.mapper;

import pl.kompikownia.pksmanager.schedulemanager.api.dto.BusStopDto;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.ScheduleDto;
import pl.kompikownia.pksmanager.schedulemanager.api.response.GetScheduleListResponse;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;

import java.util.List;
import java.util.stream.Collectors;

public class GetScheduleListResponseMapper {

    public static GetScheduleListResponse map(List<Schedule> schedules) {
        return GetScheduleListResponse.of(schedules.stream()
                .map(GetScheduleListResponseMapper::mapSchedule)
                .collect(Collectors.toList()));
    }

    private static ScheduleDto mapSchedule(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .busId(schedule.getBusId())
                .workerId(schedule.getWorkerId())
                .isActive(schedule.isActive())
                .price(schedule.getPrice())
                .busStops(schedule.getBusStops().stream()
                    .map(GetScheduleListResponseMapper::mapBusStop)
                    .collect(Collectors.toList()))
                .build();
    }

    public static BusStopDto mapBusStop(BusStop busStop) {
        return BusStopDto.builder()
                .id(busStop.getId())
                .arrivalDate(busStop.getArrivalDate())
                .departureDate(busStop.getDepartureDate())
                .scheduleId(busStop.getScheduleId())
                .townId(busStop.getTownId())
                .build();
    }
}
