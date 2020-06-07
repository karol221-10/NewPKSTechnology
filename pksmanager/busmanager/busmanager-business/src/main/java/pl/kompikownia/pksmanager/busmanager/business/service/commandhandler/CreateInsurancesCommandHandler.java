package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.busmanager.business.command.CreateInsuranceCommand;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;


@Handler
@AllArgsConstructor
public class CreateInsurancesCommandHandler extends CommandHandler<InsurancesProjection, CreateInsuranceCommand> {

    private final InsurancesRepository repository;

    @Override
    public InsurancesProjection handle(CreateInsuranceCommand command) {
        val insuranceProjection = InsurancesProjection.builder()
                .busId(command.getBusId())
                .comment(command.getComment())
                .creationDate(command.getCreationDate())
                .expiryDate(command.getExpiryDate())
                .type(command.getType())
                .build();

        return repository.save(insuranceProjection);

    }
}
