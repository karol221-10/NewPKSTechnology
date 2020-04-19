package pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants;

import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;
import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants.ScheduleDataConstants.*;

public class ScheduleDataPreparement {

    public static List<TownEntity> prepareTowns() {
        return Arrays.asList(
                new TownEntity(WARSAW_TOWN_ID,"Warszawa",null),
                new TownEntity(KIELCE_TOWN_ID,"Kielce",null),
                new TownEntity(BIALYSTOK_TOWN_ID, "Białystok", null),
                new TownEntity(SANDOMIERZ_TOWN_ID, "Sandomierz",null)
        );
    }

    public static List<ScheduleProjection> prepareSchedules() {
        return Arrays.asList(
                prepareSchedule(FIRST_SCHEDULE_ID),
                prepareSchedule(SECOND_SCHEDULE_ID)
        );
    }

    public static List<BusStopProjection> prepareBusStops() {
        return Arrays.asList(
                prepareBusStop(WARSAW_FIRST_BUS_STOP_ID, FIRST_BUS_DATE, WARSAW_TOWN_ID, FIRST_SCHEDULE_ID),
                prepareBusStop(KIELCE_FIRST_BUS_STOP_ID, SECOND_BUS_DATE, KIELCE_TOWN_ID, FIRST_SCHEDULE_ID),
                prepareBusStop(BIALYSTOK_FIRST_BUS_STOP_ID, THIRD_BUS_DATE, BIALYSTOK_TOWN_ID, FIRST_SCHEDULE_ID),
                prepareBusStop(KIELCE_SECOND_BUS_STOP_ID, FIRST_BUS_DATE, KIELCE_TOWN_ID, SECOND_SCHEDULE_ID),
                prepareBusStop(BIALYSTOK_SECOND_BUS_STOP_ID, THIRD_BUS_DATE, BIALYSTOK_TOWN_ID, SECOND_SCHEDULE_ID),
                prepareBusStop(SANDOMIERZ_SECOND_BUS_STOP_ID, SECOND_BUS_DATE, SANDOMIERZ_TOWN_ID, SECOND_SCHEDULE_ID)
                );
    }

    private static ScheduleProjection prepareSchedule(Long scheduleId) {
        return ScheduleProjection.builder()
                .id(scheduleId)
                .busId(ScheduleDataConstants.SCHEDULE_BUS_ID)
                .workerId(ScheduleDataConstants.SCHEDULE_WORKER_ID)
                .isActive(true)
                .price(ScheduleDataConstants.SCHEDULE_PRICE)
                .busStops(new ArrayList<>())
                .build();
    }

    static BusStopProjection prepareBusStop(Long busStopId, LocalDateTime arrivalDate, Long townId, Long scheduleId) {
        return BusStopProjection.builder()
                .id(busStopId)
                .arrivalDate(arrivalDate)
                .departureDate(arrivalDate.plusMinutes(5L))
                .scheduleId(scheduleId)
                .townId(townId)
                .build();
    }
}
