package pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants;

import lombok.val;
import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;

import java.util.Arrays;
import java.util.List;

public class ScheduleDataPreparement {

    public static List<TownEntity> prepareTowns() {
        return Arrays.asList(
                new TownEntity(1L,"Warszawa",null),
                new TownEntity(2L,"Kielce",null),
                new TownEntity(3L, "Białystok", null),
                new TownEntity(4L, "Sandomierz",null)
        );
    }
}
