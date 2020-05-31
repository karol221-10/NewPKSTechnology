package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.busmanager.business.command.PostBusCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

@Handler
public class PostBusCommandHandler extends CommandHandler<BusProjection, PostBusCommand> {

    private BusRepository repository;
    private CommandExecutor commandExecutor;
    private QueryExecutor queryExecutor;

    public PostBusCommandHandler(BusRepository repository, @Lazy CommandExecutor commandExecutor, @Lazy QueryExecutor queryExecutor) {
        this.repository = repository;
        this.commandExecutor = commandExecutor;
        this.queryExecutor = queryExecutor;
    }

    @Override
    public BusProjection handle(PostBusCommand command) {
        PostBusCommand postBusCommand = PostBusCommand.builder()
                .id(command.getId())
                .model(command.getModel())
                .registrationNumber(command.getRegistrationNumber())
                .inspectionProjections(command.getInspectionProjections())
                .insurancesProjections(command.getInsurancesProjections())
                .build();


        val busProjection = commandExecutor.execute(postBusCommand);
        return repository.save(busProjection);
    }
}
