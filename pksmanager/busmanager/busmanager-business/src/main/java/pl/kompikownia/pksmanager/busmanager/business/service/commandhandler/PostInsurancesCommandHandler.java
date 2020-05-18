package pl.kompikownia.pksmanager.busmanager.business.service.commandhandler;

import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.busmanager.business.command.PostInsurancesCommand;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;


@Handler
public class PostInsurancesCommandHandler extends CommandHandler<InsurancesProjection, PostInsurancesCommand> {

    private InsurancesRepository repository;

    private CommandExecutor commandExecutor;

    private QueryExecutor queryExecutor;

    public PostInsurancesCommandHandler(@Lazy CommandExecutor commandExecutor, @Lazy QueryExecutor queryExecutor, InsurancesRepository repository){
        this.commandExecutor = commandExecutor;
        this.repository = repository;
        this.queryExecutor = queryExecutor;
    }

    @Override
    public InsurancesProjection handle(PostInsurancesCommand command) {
        PostInsurancesCommand postInsurancesCommand = PostInsurancesCommand.builder()
                .id(command.getId())
                .type(command.getType())
                .creationDate(command.getCreationDate())
                .expiryDate(command.getExpiryDate())
                .comment(command.getComment())
                .busId(command.getBusId())
                .build();

        val insuranceProjection = commandExecutor.execute(postInsurancesCommand);

        return repository.save(insuranceProjection);

    }
}
