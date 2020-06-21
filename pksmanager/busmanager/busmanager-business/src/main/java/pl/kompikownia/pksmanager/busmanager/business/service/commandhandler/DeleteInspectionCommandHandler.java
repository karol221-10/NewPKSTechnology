package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.command.DeleteInspectionCommand;
import pl.kompikownia.pksmanager.busmanager.business.repository.InspectionRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;


@Slf4j
@AllArgsConstructor
@Handler
public class DeleteInspectionCommandHandler extends CommandHandler<Long, DeleteInspectionCommand> {

    private InspectionRepository repository;

    @Override
    public Long handle(DeleteInspectionCommand command) {
        repository.deleteById(command.getInspectionId());
        return command.getInspectionId();
    }
}
