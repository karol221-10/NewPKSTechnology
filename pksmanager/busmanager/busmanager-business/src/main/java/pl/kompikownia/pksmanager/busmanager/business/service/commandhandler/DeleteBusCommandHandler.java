package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.command.DeleteBusCommand;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;


@AllArgsConstructor
@Handler
public class DeleteBusCommandHandler extends CommandHandler<Long, DeleteBusCommand> {

    private BusRepository repository;

    @Override
    public Long handle(DeleteBusCommand command) {
        repository.deleteById(command.getBusId());
        return command.getBusId();
    }
}
