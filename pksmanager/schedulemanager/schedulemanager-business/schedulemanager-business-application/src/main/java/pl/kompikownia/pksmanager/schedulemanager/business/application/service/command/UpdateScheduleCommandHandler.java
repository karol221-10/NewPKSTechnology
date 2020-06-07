package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.UpdateScheduleCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.SimpleSchedule;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.ScheduleRepository;

@Handler
@AllArgsConstructor
public class UpdateScheduleCommandHandler extends CommandHandler<SimpleSchedule, UpdateScheduleCommand> {

    private ScheduleRepository scheduleRepository;

    @Override
    public SimpleSchedule handle(UpdateScheduleCommand command) {
        val projection = scheduleRepository.findById(Long.parseLong(command.getId()));
        projection.setWorkerId(Long.parseLong(command.getWorkerId()));
        projection.setBusId(Long.parseLong(command.getBusId()));
        projection.setActive(command.isActive());
        val updatedResult = scheduleRepository.update(projection);
        return SimpleSchedule.builder()
                .id(updatedResult.getId().toString())
                .isActive(updatedResult.isActive())
                .busId(updatedResult.getBusId().toString())
                .workerId(updatedResult.getWorkerId().toString())
                .build();
    }
}
