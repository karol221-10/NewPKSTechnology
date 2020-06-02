package pl.kompikownia.pksmanager.schedulemanager.test.test.constants;


import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;

import java.util.Arrays;
import java.util.List;

public class ScheduleDataExpectations {

    public static List<BusStop> getExpectatedBusStopsFromWarsawToBialystok() {
        return Arrays.asList(
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.WARSAW_FIRST_BUS_STOP_ID, ScheduleDataConstants.FIRST_BUS_DATE, ScheduleDataConstants.WARSAW_TOWN_ID, ScheduleDataConstants.FIRST_SCHEDULE_ID),
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.KIELCE_FIRST_BUS_STOP_ID, ScheduleDataConstants.SECOND_BUS_DATE, ScheduleDataConstants.KIELCE_TOWN_ID, ScheduleDataConstants.FIRST_SCHEDULE_ID),
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.BIALYSTOK_FIRST_BUS_STOP_ID, ScheduleDataConstants.THIRD_BUS_DATE, ScheduleDataConstants.BIALYSTOK_TOWN_ID, ScheduleDataConstants.FIRST_SCHEDULE_ID)
        );
    }

    public static List<BusStop> getExpectatedBusStopsFromKielceToSandomierz() {
        return Arrays.asList(
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.KIELCE_SECOND_BUS_STOP_ID, ScheduleDataConstants.FIRST_BUS_DATE, ScheduleDataConstants.KIELCE_TOWN_ID, ScheduleDataConstants.SECOND_SCHEDULE_ID),
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.BIALYSTOK_SECOND_BUS_STOP_ID, ScheduleDataConstants.SECOND_BUS_DATE, ScheduleDataConstants.BIALYSTOK_TOWN_ID, ScheduleDataConstants.SECOND_SCHEDULE_ID),
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.SANDOMIERZ_SECOND_BUS_STOP_ID, ScheduleDataConstants.THIRD_BUS_DATE, ScheduleDataConstants.SANDOMIERZ_TOWN_ID, ScheduleDataConstants.SECOND_SCHEDULE_ID)
        );
    }

    public static List<BusStop> getExpectatedBusStopsFromKielceToBialystok() {
        return Arrays.asList(
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.KIELCE_FIRST_BUS_STOP_ID, ScheduleDataConstants.SECOND_BUS_DATE, ScheduleDataConstants.KIELCE_TOWN_ID, ScheduleDataConstants.FIRST_SCHEDULE_ID),
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.BIALYSTOK_FIRST_BUS_STOP_ID, ScheduleDataConstants.THIRD_BUS_DATE, ScheduleDataConstants.BIALYSTOK_TOWN_ID, ScheduleDataConstants.FIRST_SCHEDULE_ID),
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.KIELCE_SECOND_BUS_STOP_ID, ScheduleDataConstants.FIRST_BUS_DATE, ScheduleDataConstants.KIELCE_TOWN_ID, ScheduleDataConstants.SECOND_SCHEDULE_ID),
                ScheduleDataPreparement.prepareBusStop(ScheduleDataConstants.BIALYSTOK_SECOND_BUS_STOP_ID, ScheduleDataConstants.SECOND_BUS_DATE, ScheduleDataConstants.BIALYSTOK_TOWN_ID, ScheduleDataConstants.SECOND_SCHEDULE_ID)
        );
    }
}
