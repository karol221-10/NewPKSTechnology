package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.DeleteBusStopCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;

@Handler
@AllArgsConstructor
public class DeleteBusStopCommandHandler extends CommandHandler<Void, DeleteBusStopCommand> {

    private BusStopRepository busStopRepository;

    @Override
    public Void handle(DeleteBusStopCommand command) {
        busStopRepository.deleteById(Long.parseLong(command.getBusStopId()));
        return null;
    }
}
