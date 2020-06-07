package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.AddTownCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Town;
import pl.kompikownia.pksmanager.schedulemanager.business.application.mapper.TownProjectionMapper;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.TownRepository;


@Handler
public class AddTownCommandHandler extends CommandHandler<Town, AddTownCommand> {

    private CommandExecutor commandExecutor;

    private TownRepository townRepository;

    public AddTownCommandHandler(@Lazy CommandExecutor commandExecutor, TownRepository townRepository) {
        this.commandExecutor = commandExecutor;
        this.townRepository = townRepository;
    }

    @Override
    public Town handle(AddTownCommand command) {
        val town = commandExecutor.execute(command);
        townRepository.save(TownProjectionMapper.map(town));
        return town;
    }
}
