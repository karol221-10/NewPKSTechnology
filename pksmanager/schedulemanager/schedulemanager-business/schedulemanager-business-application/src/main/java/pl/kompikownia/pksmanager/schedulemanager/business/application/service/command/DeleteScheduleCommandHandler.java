package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.DeleteScheduleCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.ScheduleRepository;

@Handler
@AllArgsConstructor
public class DeleteScheduleCommandHandler extends CommandHandler<Void, DeleteScheduleCommand> {

    private ScheduleRepository scheduleRepository;

    @Override
    public Void handle(DeleteScheduleCommand command) {
        scheduleRepository.deleteById(Long.parseLong(command.getScheduleId()));
        return null;
    }
}
