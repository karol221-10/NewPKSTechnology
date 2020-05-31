package pl.kompikownia.pksmanager.schedulemanager.business.application.service.query;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetAllSchedulesQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;
import pl.kompikownia.pksmanager.schedulemanager.business.application.mapper.ScheduleMapper;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.ScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Handler
public class GetAllSchedulesQueryHandler extends QueryHandler<List<Schedule>, GetAllSchedulesQuery> {

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> handle(GetAllSchedulesQuery query) {
        return scheduleRepository.findAll().stream()
                .map(ScheduleMapper::map)
                .collect(Collectors.toList());
    }
}
