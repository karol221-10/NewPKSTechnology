package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.busmanager.business.command.PostInspectionCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.InspectionRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

@Handler
public class PostInspectionCommandHandler extends CommandHandler<InspectionProjection, PostInspectionCommand> {

    private InspectionRepository repository;
    private CommandExecutor commandExecutor;
    private QueryExecutor queryExecutor;

    public PostInspectionCommandHandler(InspectionRepository repository, @Lazy CommandExecutor commandExecutor,@Lazy QueryExecutor queryExecutor) {
        this.repository = repository;
        this.commandExecutor = commandExecutor;
        this.queryExecutor = queryExecutor;
    }

    @Override
    public InspectionProjection handle(PostInspectionCommand command) {
        PostInspectionCommand postInspectionCommand = PostInspectionCommand.builder()
                .id(command.getId())
                .type(command.getType())
                .creationDate(command.getCreationDate())
                .expiryDate(command.getExpiryDate())
                .comment(command.getComment())
                .busId(command.getBusId())
                .build();

        val inspectionProjection = commandExecutor.execute(postInspectionCommand);
        return repository.save(inspectionProjection);
    }
}
