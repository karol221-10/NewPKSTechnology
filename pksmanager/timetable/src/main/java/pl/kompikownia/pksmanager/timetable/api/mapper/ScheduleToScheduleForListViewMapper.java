package pl.kompikownia.pksmanager.timetable.api.mapper;

import lombok.val;
import pl.kompikownia.pksmanager.timetable.api.response.ScheduleForListView;
import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;

import java.time.Duration;
import java.time.LocalDateTime;

public class ScheduleToScheduleForListViewMapper {
    public static ScheduleForListView map(ScheduleProjection scheduleProjection) {
        return ScheduleForListView.builder()
                .id(scheduleProjection.getId())
                .leavingFrom(getFirstBusStopTown(scheduleProjection))
                .leavingTo(getLastBusStopTown(scheduleProjection))
                .date(getFirstBusStopDepartureDate(scheduleProjection))
                .passengers(0) //TODO - get list of free tickets
                .numberOfStops(scheduleProjection.getBusStops().size())
                .travelTime(getTravelTime(scheduleProjection))
                .build();
    }

    private static LocalDateTime getFirstBusStopDepartureDate(ScheduleProjection scheduleProjection) {
        return scheduleProjection.getBusStops().get(0).getDepartureDate();
    }

    private static Long getLastBusStopTown(ScheduleProjection scheduleProjection) {
        return scheduleProjection.getBusStops().get(scheduleProjection.getBusStops().size()-1).getTownId();
    }

    private static Long getFirstBusStopTown(ScheduleProjection scheduleProjection) {
        return scheduleProjection.getBusStops().get(0).getTownId();
    }

    private static Duration getTravelTime(ScheduleProjection scheduleProjection) {
        val lastBusStopArrivalDate = scheduleProjection.getBusStops().get(scheduleProjection.getBusStops().size()-1).getArrivalDate();
        val firstBusStopDepartureDate = scheduleProjection.getBusStops().get(0).getDepartureDate();
        return Duration.between(lastBusStopArrivalDate,firstBusStopDepartureDate);
    }
}
