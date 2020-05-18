package pl.kompikownia.pksmanager.schedulemanager.test.test.constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduleDataConstants {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final Long SCHEDULE_BUS_ID = 1L;
    public static final Long SCHEDULE_WORKER_ID = 1L;
    public static final float SCHEDULE_PRICE = 15;
    public static final LocalDateTime FIRST_BUS_DATE = LocalDateTime.parse("18-04-2020 09:00:00",DATE_TIME_FORMATTER);
    public static final LocalDateTime SECOND_BUS_DATE = LocalDateTime.parse("18-04-2020 10:00:00", DATE_TIME_FORMATTER);
    public static final LocalDateTime THIRD_BUS_DATE = LocalDateTime.parse("18-04-2020 11:00:00", DATE_TIME_FORMATTER);

    public static final Long WARSAW_FIRST_BUS_STOP_ID = 1L;
    public static final Long KIELCE_FIRST_BUS_STOP_ID = 2L;
    public static final Long BIALYSTOK_FIRST_BUS_STOP_ID = 3L;

    public static final Long KIELCE_SECOND_BUS_STOP_ID = 4L;
    public static final Long BIALYSTOK_SECOND_BUS_STOP_ID = 5L;
    public static final Long SANDOMIERZ_SECOND_BUS_STOP_ID = 6L;

    public static final Long WARSAW_TOWN_ID = 1L;
    public static final Long KIELCE_TOWN_ID = 2L;
    public static final Long BIALYSTOK_TOWN_ID = 3L;
    public static final Long SANDOMIERZ_TOWN_ID = 4L;

    public static final Long FIRST_SCHEDULE_ID = 1L;
    public static final Long SECOND_SCHEDULE_ID = 2L;
}
