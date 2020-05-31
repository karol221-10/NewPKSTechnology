package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.AddNewScheduleCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;
import pl.kompikownia.pksmanager.schedulemanager.business.application.mapper.BusStopProjectionMapper;
import pl.kompikownia.pksmanager.schedulemanager.business.application.mapper.ScheduleMapper;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.ScheduleRepository;

import java.util.stream.Collectors;

@Handler
@AllArgsConstructor
public class AddNewScheduleCommandHandler extends CommandHandler<Schedule, AddNewScheduleCommand> {

    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule handle(AddNewScheduleCommand command) {
        val scheduleProjection = ScheduleProjection.builder()
                .busId(command.getBusId())
                .isActive(true)
                .workerId(command.getWorkerId())
                .busStops(command.getBusStops().stream()
                .map(BusStopProjectionMapper::map)
                .collect(Collectors.toList()))
                .build();
        val result = scheduleRepository.save(scheduleProjection);
        return ScheduleMapper.map(result);
    }
}
