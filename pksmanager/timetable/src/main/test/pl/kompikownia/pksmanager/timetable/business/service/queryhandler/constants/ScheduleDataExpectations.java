package pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants;


import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;

import java.util.Arrays;
import java.util.List;

import static pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants.ScheduleDataConstants.*;
import static pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants.ScheduleDataPreparement.prepareBusStop;

public class ScheduleDataExpectations {

    public static List<BusStopProjection> getExpectatedBusStopsFromWarsawToBialystok() {
        return Arrays.asList(
                prepareBusStop(WARSAW_FIRST_BUS_STOP_ID, ScheduleDataConstants.FIRST_BUS_DATE, WARSAW_TOWN_ID, FIRST_SCHEDULE_ID),
                prepareBusStop(KIELCE_FIRST_BUS_STOP_ID, ScheduleDataConstants.SECOND_BUS_DATE, KIELCE_TOWN_ID, FIRST_SCHEDULE_ID),
                prepareBusStop(BIALYSTOK_FIRST_BUS_STOP_ID, ScheduleDataConstants.THIRD_BUS_DATE, BIALYSTOK_TOWN_ID, FIRST_SCHEDULE_ID)
        );
    }

    public static List<BusStopProjection> getExpectatedBusStopsFromKielceToSandomierz() {
        return Arrays.asList(
                prepareBusStop(KIELCE_SECOND_BUS_STOP_ID, FIRST_BUS_DATE, KIELCE_TOWN_ID, SECOND_SCHEDULE_ID),
                prepareBusStop(BIALYSTOK_SECOND_BUS_STOP_ID, SECOND_BUS_DATE, BIALYSTOK_TOWN_ID, SECOND_SCHEDULE_ID),
                prepareBusStop(SANDOMIERZ_SECOND_BUS_STOP_ID, THIRD_BUS_DATE, SANDOMIERZ_TOWN_ID, SECOND_SCHEDULE_ID)
        );
    }

    public static List<BusStopProjection> getExpectatedBusStopsFromKielceToBialystok() {
        return Arrays.asList(
                prepareBusStop(KIELCE_FIRST_BUS_STOP_ID, SECOND_BUS_DATE, KIELCE_TOWN_ID, FIRST_SCHEDULE_ID),
                prepareBusStop(BIALYSTOK_FIRST_BUS_STOP_ID, THIRD_BUS_DATE, BIALYSTOK_TOWN_ID, FIRST_SCHEDULE_ID),
                prepareBusStop(KIELCE_SECOND_BUS_STOP_ID, FIRST_BUS_DATE, KIELCE_TOWN_ID, SECOND_SCHEDULE_ID),
                prepareBusStop(BIALYSTOK_SECOND_BUS_STOP_ID, SECOND_BUS_DATE, BIALYSTOK_TOWN_ID, SECOND_SCHEDULE_ID)
        );
    }
}
