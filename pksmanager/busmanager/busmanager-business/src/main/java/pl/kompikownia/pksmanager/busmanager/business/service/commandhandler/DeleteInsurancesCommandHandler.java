package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.command.DeleteInspectionCommand;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;


@Slf4j
@AllArgsConstructor
@Handler
public class DeleteInsurancesCommandHandler extends CommandHandler<Long, DeleteInspectionCommand> {

    private InsurancesRepository repository;

    @Override
    public Long handle(DeleteInspectionCommand command) {
        repository.deleteById(command.getInspectionId());
        return command.getInspectionId();
    }
}
