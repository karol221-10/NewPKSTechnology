package pl.kompikownia.pksmanager.timetable.business.service.queryhandler;


import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.timetable.base.IntegrationTest;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetScheduleListQuery;
import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;
import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.timetable.business.repository.BusStopRepository;
import pl.kompikownia.pksmanager.timetable.business.repository.ScheduleEntityRepository;
import pl.kompikownia.pksmanager.timetable.business.repository.TownEntityRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants.ScheduleDataConstants.*;
import static pl.kompikownia.pksmanager.timetable.business.service.queryhandler.constants.ScheduleDataExpectations.*;

@Slf4j
@Sql("/GetScheduleListTest.sql")
public class GetScheduleListQueryHandlerTest extends IntegrationTest {

    @Autowired
    private BusStopRepository busStopRepository;

    @Autowired
    private ScheduleEntityRepository scheduleEntityRepository;

    @Autowired
    private TownEntityRepository townEntityRepository;

    @Autowired
    private QueryExecutor queryExecutor;

    @BeforeEach
    @Transactional
    void prepareDatabase() {
      /*  busStopRepository.deleteAll();
        scheduleEntityRepository.deleteAll();
        townEntityRepository.deleteAll();
        ScheduleDataPreparement.prepareTowns()
                .forEach(townEntity -> townEntityRepository.save(townEntity));
        ScheduleDataPreparement.prepareSchedules()
                .forEach(scheduleProjection -> scheduleEntityRepository.save(scheduleProjection));
        ScheduleDataPreparement.prepareBusStops()
                .forEach(busStopProjection -> busStopRepository.save(busStopProjection));*/
    }

    private static Stream<Arguments> provideDataForScheduleTest() {
        return Stream.of(
                Arguments.of(WARSAW_TOWN_ID, BIALYSTOK_TOWN_ID, 1, getExpectatedBusStopsFromWarsawToBialystok()),
                Arguments.of(KIELCE_TOWN_ID, SANDOMIERZ_TOWN_ID, 1, getExpectatedBusStopsFromKielceToSandomierz()),
                Arguments.of(KIELCE_TOWN_ID, BIALYSTOK_TOWN_ID, 2, getExpectatedBusStopsFromKielceToBialystok())
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForScheduleTest")
    public void shouldReturnSingleValidScheduleWithBusStops(Long sourceTownId,
                                                      Long destinationTownId,
                                                      Integer expectatedSchedules,
                                                      List<BusStopProjection> expectatedValues) {
        // given

        // when
        val result = queryExecutor.execute(GetScheduleListQuery.builder()
            .sourceTownId(sourceTownId)
            .destinationTownId(destinationTownId)
            .build()
        );

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expectatedSchedules);
        result.forEach(singleResult -> {
            assertSingleResult(expectatedValues, singleResult);
        });
    }

    private void assertSingleResult(List<BusStopProjection> expectatedValues, ScheduleProjection singleResult) {
        assertThat(singleResult.getBusStops()).isNotNull();
        assertThat(singleResult.getBusStops().size())
                .isEqualTo(getExpectatedValuesSize(expectatedValues, singleResult));
        val schedulesToTest = getAppropriateExpectedValues(expectatedValues, singleResult);

        schedulesToTest.forEach(scheduleToTest -> {
            assertThat(singleResult.getBusStops()).contains(scheduleToTest);
        });
    }

    private List<BusStopProjection> getAppropriateExpectedValues(List<BusStopProjection> expectatedValues, ScheduleProjection singleResult) {
        return expectatedValues.stream()
                .filter(expectated -> expectated.getScheduleId().equals(singleResult.getId()))
                .collect(Collectors.toList());
    }

    private long getExpectatedValuesSize(List<BusStopProjection> expectatedValues, ScheduleProjection singleResult) {
        return expectatedValues.stream()
                .filter(expectated -> expectated.getScheduleId().equals(singleResult.getId()))
                .count();
    }
}
