package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.command.DeleteInsurancesCommand;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;


@Slf4j
@AllArgsConstructor
@Handler
public class DeleteInsurancesCommandHandler extends CommandHandler<Long, DeleteInsurancesCommand> {

    private InsurancesRepository repository;

    @Override
    public Long handle(DeleteInsurancesCommand command) {
        repository.deleteById(command.getInsurancesId());
        return command.getInsurancesId();
    }
}
