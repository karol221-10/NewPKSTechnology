package pl.kompikownia.pksmanager.schedulemanager.test.test;


import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.schedulemanager.test.base.IntegrationTest;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetScheduleListQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.ScheduleRepository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.TownRepository;
import pl.kompikownia.pksmanager.schedulemanager.test.test.constants.ScheduleDataConstants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.kompikownia.pksmanager.schedulemanager.test.test.constants.ScheduleDataExpectations.*;

@Slf4j
@Sql("/GetScheduleListTest.sql")
public
class GetScheduleListQueryHandlerTest extends IntegrationTest {

    @Autowired
    private BusStopRepository busStopRepository;

    @Autowired
    private ScheduleRepository scheduleEntityRepository;

    @Autowired
    private TownRepository townEntityRepository;

    @Autowired
    private QueryExecutor queryExecutor;

    private static Stream<Arguments> provideDataForScheduleTest() {
        return Stream.of(
                Arguments.of(ScheduleDataConstants.WARSAW_TOWN_ID, ScheduleDataConstants.BIALYSTOK_TOWN_ID, 1, getExpectatedBusStopsFromWarsawToBialystok()),
                Arguments.of(ScheduleDataConstants.KIELCE_TOWN_ID, ScheduleDataConstants.SANDOMIERZ_TOWN_ID, 1, getExpectatedBusStopsFromKielceToSandomierz()),
                Arguments.of(ScheduleDataConstants.KIELCE_TOWN_ID, ScheduleDataConstants.BIALYSTOK_TOWN_ID, 2, getExpectatedBusStopsFromKielceToBialystok())
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForScheduleTest")
    public void shouldReturnSingleValidScheduleWithBusStops(Long sourceTownId,
                                                      Long destinationTownId,
                                                      Integer expectatedSchedules,
                                                      List<BusStop> expectatedValues) {
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

    private void assertSingleResult(List<BusStop> expectatedValues, Schedule singleResult) {
        assertThat(singleResult.getBusStops()).isNotNull();
        assertThat(singleResult.getBusStops().size())
                .isEqualTo(getExpectatedValuesSize(expectatedValues, singleResult));
        val schedulesToTest = getAppropriateExpectedValues(expectatedValues, singleResult);

        schedulesToTest.forEach(scheduleToTest -> {
            assertThat(singleResult.getBusStops()).contains(scheduleToTest);
        });
    }

    private List<BusStop> getAppropriateExpectedValues(List<BusStop> expectatedValues, Schedule singleResult) {
        return expectatedValues.stream()
                .filter(expectated -> expectated.getScheduleId().equals(singleResult.getId()))
                .collect(Collectors.toList());
    }

    private long getExpectatedValuesSize(List<BusStop> expectatedValues, Schedule singleResult) {
        return expectatedValues.stream()
                .filter(expectated -> expectated.getScheduleId().equals(singleResult.getId()))
                .count();
    }
}
