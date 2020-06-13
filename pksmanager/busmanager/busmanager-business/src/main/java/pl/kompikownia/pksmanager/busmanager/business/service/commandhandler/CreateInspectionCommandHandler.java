package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.busmanager.business.command.CreateInspectionCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.InspectionRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

@Handler
@AllArgsConstructor
public class CreateInspectionCommandHandler extends CommandHandler<InspectionProjection, CreateInspectionCommand> {

    private final InspectionRepository repository;

    @Override
    public InspectionProjection handle(CreateInspectionCommand command) {

        val inspectionProjection = InspectionProjection.builder()
                .busId(command.getBusId())
                .comment(command.getComment())
                .creationDate(command.getCreationDate())
                .expiryDate(command.getExpiryDate())
                .type(command.getType())
                .build();

        return repository.save(inspectionProjection);
    }
}
