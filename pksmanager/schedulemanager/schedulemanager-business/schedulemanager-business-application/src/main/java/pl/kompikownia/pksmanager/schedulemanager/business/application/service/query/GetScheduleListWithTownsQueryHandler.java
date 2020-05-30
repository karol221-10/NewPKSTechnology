package pl.kompikownia.pksmanager.schedulemanager.business.application.service.query;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetScheduleListWithTownsQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;
import pl.kompikownia.pksmanager.schedulemanager.business.application.mapper.ScheduleMapper;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.ScheduleRepository;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@AllArgsConstructor
@Handler
public class GetScheduleListWithTownsQueryHandler extends QueryHandler<List<Schedule>, GetScheduleListWithTownsQuery> {

    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public List<Schedule> handle(GetScheduleListWithTownsQuery query) {
        return handleInternal(query.getSourceTownId(), query.getDestinationTownId());
    }

    public List<Schedule> handleInternal(long sourceTownId, long destinationTownId) {
        val existingSchedules = scheduleRepository.findCoursesByTownId(sourceTownId, destinationTownId);

        val schedulesWithNeededTowns = existingSchedules.stream()
                .filter(containsFromAndToTowns(sourceTownId, destinationTownId))
                .collect(Collectors.toList());

        schedulesWithNeededTowns
                .forEach(scheduleProjection ->
                        cutListToContainOnlyBetweenBusStops(sourceTownId, destinationTownId, scheduleProjection));
        return schedulesWithNeededTowns.stream()
                .map(ScheduleMapper::map)
                .collect(Collectors.toList());
    }

    private Predicate<ScheduleProjection> containsFromAndToTowns(Long sourceTownId, Long destinationTownId) {
        return scheduleProjection ->
                containsTown(sourceTownId, scheduleProjection)
                        && containsTown(destinationTownId, scheduleProjection);
    }

    private boolean containsTown(Long sourceTownId, ScheduleProjection scheduleProjection) {
        return scheduleProjection.getBusStops().stream()
                .anyMatch(busStopProjection -> busStopProjection.getTownId().equals(sourceTownId));
    }

    private void cutListToContainOnlyBetweenBusStops(Long sourceTown,
                                                     Long destinationTown,
                                                     ScheduleProjection projection) {
        val busStops = projection.getBusStops().stream()
                .sorted(Comparator.comparing(BusStopProjection::getDepartureDate))
                .collect(Collectors.toList());
        val beginIndex = getBusStopIndex(sourceTown, busStops);
        val destinationIndex = getBusStopIndex(destinationTown, busStops);
        val newList = busStops.subList(beginIndex, destinationIndex + 1);
        projection.setBusStops(newList);
    }

    private int getBusStopIndex(Long sourceTown, List<BusStopProjection> projection) {
        return IntStream.range(0, projection.size())
                .filter(busStop -> projection.get(busStop).getTownId().equals(sourceTown))
                .findFirst()
                .getAsInt();
    }
}

