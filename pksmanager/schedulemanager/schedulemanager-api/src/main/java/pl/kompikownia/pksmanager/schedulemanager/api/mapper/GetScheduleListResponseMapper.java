package pl.kompikownia.pksmanager.schedulemanager.api.mapper;

import lombok.val;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.BusStopDto;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.ScheduleDto;
import pl.kompikownia.pksmanager.schedulemanager.api.response.GetScheduleListResponse;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;

import java.time.temporal.ChronoUnit;
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
                .totalDistance(calculateTotalDistance(schedule.getBusStops()))
                .totalTimeSeconds(calculateTotalTime(schedule.getBusStops()))
                .build();
    }

    public static BusStopDto mapBusStop(BusStop busStop) {
        return BusStopDto.builder()
                .id(busStop.getId())
                .arrivalDate(busStop.getArrivalDate())
                .departureDate(busStop.getDepartureDate())
                .scheduleId(busStop.getScheduleId())
                .townId(busStop.getTownId())
                .distanceFromPrev(busStop.getDistanceFromPrev())
                .price(busStop.getPrice())
                .build();
    }

    private static Double calculateTotalDistance(List<BusStop> busStops) {
        return busStops.stream()
                .map(BusStop::getDistanceFromPrev)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private static Long calculateTotalTime(List<BusStop> busStops) {
        val minTime = busStops.stream()
                .min((busStop1, busStop2) -> busStop1.getArrivalDate().compareTo(busStop2.getDepartureDate()));
        val maxTime = busStops.stream()
                .max((busStop1, busStop2) -> busStop1.getArrivalDate().compareTo(busStop2.getDepartureDate()));
        if(minTime.isPresent() && maxTime.isPresent()) {
            return minTime.get().getArrivalDate().until(maxTime.get().getDepartureDate(), ChronoUnit.SECONDS);
        }
        return 0L;
    }
}
