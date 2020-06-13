package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.busmanager.business.command.CreateBusCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.SimpleBusProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

@Handler
public class CreateBusCommandHandler extends CommandHandler<SimpleBusProjection, CreateBusCommand> {

    private BusRepository repository;
    private CommandExecutor commandExecutor;
    private QueryExecutor queryExecutor;

    public CreateBusCommandHandler(BusRepository repository, @Lazy CommandExecutor commandExecutor, @Lazy QueryExecutor queryExecutor) {
        this.repository = repository;
        this.commandExecutor = commandExecutor;
        this.queryExecutor = queryExecutor;
    }

    @Override
    public SimpleBusProjection handle(CreateBusCommand command) {
        val busProjection = SimpleBusProjection.builder()
                .id(command.getId())
                .model(command.getModel())
                .registrationNumber(command.getRegistrationNumber())
                .build();
        return repository.save(busProjection);
    }
}
