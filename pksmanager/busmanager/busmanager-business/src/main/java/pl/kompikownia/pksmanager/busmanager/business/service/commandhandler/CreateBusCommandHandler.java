package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.busmanager.business.command.CreateBusCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

@Handler
@AllArgsConstructor
public class CreateBusCommandHandler extends CommandHandler<BusProjection, CreateBusCommand> {

    private BusRepository repository;
    private CommandExecutor commandExecutor;
    private QueryExecutor queryExecutor;

    @Override
    public BusProjection handle(CreateBusCommand command) {
        val busProjection = BusProjection.builder()
                .id(command.getId())
                .model(command.getModel())
                .registrationNumber(command.getRegistrationNumber())
                .insurancesProjections(command.getInsurancesProjections())
                .inspectionProjections(command.getInspectionProjections())
                .build();
        val result = repository.save(busProjection);

        return BusProjection.builder()
                .id(result.getId())
                .model(result.getModel())
                .registrationNumber(result.getRegistrationNumber())
                .inspectionProjections(result.getInspectionProjections())
                .insurancesProjections(result.getInsurancesProjections())
                .build();
    }
}
