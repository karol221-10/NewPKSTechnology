package pl.kompikownia.pksmanager.timetable.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetScheduleListQuery;
import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;
import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa.ScheduleEntityRepositoryImpl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@AllArgsConstructor
@Handler
public class GetScheduleListQueryHandler extends QueryHandler<List<ScheduleProjection>, GetScheduleListQuery> {

    ScheduleEntityRepositoryImpl repository;

    @Override
    public List<ScheduleProjection> handle(GetScheduleListQuery query) {
        return handleInternal(query.getSourceTownId(), query.getDestinationTownId());
    }

    public List<ScheduleProjection> handleInternal(long sourceTownId, long destinationTownId) {
        val existingSchedules = repository.findCoursesByTownId(sourceTownId,destinationTownId);

        val schedulesWithNeededTowns = existingSchedules.stream()
                .filter(containsFromAndToTowns(sourceTownId, destinationTownId))
                .collect(Collectors.toList());

        schedulesWithNeededTowns
                .forEach(scheduleProjection ->
                        cutListToContainOnlyBetweenBusStops(sourceTownId, destinationTownId, scheduleProjection));

        return schedulesWithNeededTowns;
    }

    private Predicate<ScheduleProjection> containsFromAndToTowns(Long sourceTownId, Long destinationTownId) {
        return scheduleProjection ->
                containsTown(sourceTownId, scheduleProjection)
                && containsTown(destinationTownId, scheduleProjection);
    }

    private boolean containsTown(Long sourceTownId, ScheduleProjection scheduleProjection) {
        return scheduleProjection.getBusStops().stream()
                .anyMatch(busStopProjection -> busStopProjection.getId().equals(sourceTownId));
    }

    private void cutListToContainOnlyBetweenBusStops(Long sourceTown,
                                                     Long destinationTown,
                                                     ScheduleProjection projection) {
        val beginIndex = getBusStopIndex(sourceTown, projection);
        val destinationIndex = getBusStopIndex(destinationTown, projection);
        projection.setBusStops(subListBusStopList(projection, beginIndex, destinationIndex));
    }

    private List<BusStopProjection> subListBusStopList(ScheduleProjection projection, int beginIndex, int destinationIndex) {
        return projection.getBusStops().subList(beginIndex, destinationIndex);
    }

    private int getBusStopIndex(Long sourceTown, ScheduleProjection projection) {
        return IntStream.range(0, projection.getBusStops().size())
                .filter(busStop -> projection.getBusStops().get(busStop).getId().equals(sourceTown))
                .findFirst()
                .getAsInt();
    }




}
