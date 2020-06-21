package pl.kompikownia.pksmanager.schedulemanager.business.application.service.internal.query;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.ScheduleRepository;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.query.GetScheduleByIdInternalQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.response.Schedule;

import java.util.stream.Collectors;

@Handler
@AllArgsConstructor
public class GetScheduleByIdInternalQueryHandler extends QueryHandler<Schedule, GetScheduleByIdInternalQuery> {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule handle(GetScheduleByIdInternalQuery query) {
        val schedule = scheduleRepository.findById(Long.parseLong(query.getScheduleId()));
        return Schedule.builder()
                .id(schedule.getId())
                .price(schedule.getPrice())
                .busStops(schedule.getBusStops().stream()
                            .map(this::map)
                            .collect(Collectors.toList()))
                .build();
    }

    private BusStop map(BusStopProjection busStopProjection) {
        return BusStop.builder()
                .arrivalDate(busStopProjection.getArrivalDate())
                .departureDate(busStopProjection.getDepartureDate())
                .distanceFromPrev(busStopProjection.getDistanceFromPrev())
                .price(busStopProjection.getPrice())
                .id(busStopProjection.getId())
                .townId(busStopProjection.getTownId())
                .build();
    }
}
