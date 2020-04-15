package pl.kompikownia.pksmanager.timetable.business.service.queryhandler;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kompikownia.pksmanager.timetable.base.IntegrationTest;
import pl.kompikownia.pksmanager.timetable.business.repository.BusStopRepository;
import pl.kompikownia.pksmanager.timetable.business.repository.ScheduleEntityRepository;
import pl.kompikownia.pksmanager.timetable.business.repository.TownEntityRepository;
import pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants.ScheduleDataPreparement;

import javax.transaction.Transactional;

public class GetScheduleListQueryHandlerTest extends IntegrationTest {

    @Autowired
    private BusStopRepository busStopRepository;

    @Autowired
    private ScheduleEntityRepository scheduleEntityRepository;

    @Autowired
    private TownEntityRepository townEntityRepository;

    @BeforeEach
    void prepareDatabase() {
        busStopRepository.deleteAll();
        scheduleEntityRepository.deleteAll();
        townEntityRepository.deleteAll();
        ScheduleDataPreparement.prepareTowns()
                .forEach(townEntity -> townEntityRepository.save(townEntity));
    }

    @Test
    @Transactional
    public void shouldReturnScheduleWithBusStops() {
        //given

    }
}
