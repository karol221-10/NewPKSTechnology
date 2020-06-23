package pl.kompikownia.pksmanager.schedulemanager.test.test.constants;

import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleDataPreparement {


    public static List<Schedule> prepareSchedules() {
        return Arrays.asList(
                prepareSchedule(ScheduleDataConstants.FIRST_SCHEDULE_ID),
                prepareSchedule(ScheduleDataConstants.SECOND_SCHEDULE_ID)
        );
    }

    public static List<BusStop> prepareBusStops() {
        return Arrays.asList(
                prepareBusStop(ScheduleDataConstants.WARSAW_FIRST_BUS_STOP_ID, ScheduleDataConstants.FIRST_BUS_DATE, ScheduleDataConstants.WARSAW_TOWN_ID, ScheduleDataConstants.FIRST_SCHEDULE_ID),
                prepareBusStop(ScheduleDataConstants.KIELCE_FIRST_BUS_STOP_ID, ScheduleDataConstants.SECOND_BUS_DATE, ScheduleDataConstants.KIELCE_TOWN_ID, ScheduleDataConstants.FIRST_SCHEDULE_ID),
                prepareBusStop(ScheduleDataConstants.BIALYSTOK_FIRST_BUS_STOP_ID, ScheduleDataConstants.THIRD_BUS_DATE, ScheduleDataConstants.BIALYSTOK_TOWN_ID, ScheduleDataConstants.FIRST_SCHEDULE_ID),
                prepareBusStop(ScheduleDataConstants.KIELCE_SECOND_BUS_STOP_ID, ScheduleDataConstants.FIRST_BUS_DATE, ScheduleDataConstants.KIELCE_TOWN_ID, ScheduleDataConstants.SECOND_SCHEDULE_ID),
                prepareBusStop(ScheduleDataConstants.BIALYSTOK_SECOND_BUS_STOP_ID, ScheduleDataConstants.THIRD_BUS_DATE, ScheduleDataConstants.BIALYSTOK_TOWN_ID, ScheduleDataConstants.SECOND_SCHEDULE_ID),
                prepareBusStop(ScheduleDataConstants.SANDOMIERZ_SECOND_BUS_STOP_ID, ScheduleDataConstants.SECOND_BUS_DATE, ScheduleDataConstants.SANDOMIERZ_TOWN_ID, ScheduleDataConstants.SECOND_SCHEDULE_ID)
                );
    }

    private static Schedule prepareSchedule(Long scheduleId) {
        return Schedule.builder()
                .id(scheduleId)
                .busId(ScheduleDataConstants.SCHEDULE_BUS_ID)
                .workerId(ScheduleDataConstants.SCHEDULE_WORKER_ID)
                .isActive(true)
                .price(ScheduleDataConstants.SCHEDULE_PRICE)
                .busStops(new ArrayList<>())
                .build();
    }

    static BusStop prepareBusStop(Long busStopId, LocalDateTime arrivalDate, Long townId, Long scheduleId) {
        return BusStop.builder()
                .id(busStopId.toString())
                .arrivalDate(arrivalDate)
                .departureDate(arrivalDate.plusMinutes(5L))
                .scheduleId(scheduleId.toString())
                .townId(townId.toString())
                .build();
    }
}
