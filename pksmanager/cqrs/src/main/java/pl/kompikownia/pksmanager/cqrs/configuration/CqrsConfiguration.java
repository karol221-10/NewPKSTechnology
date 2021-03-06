package pl.kompikownia.pksmanager.cqrs.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kompikownia.pksmanager.cqrs.domain.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class CqrsConfiguration {

    @Autowired(required = false)
    private Collection<QueryHandler> queryHandlers = new ArrayList<>();

    @Autowired(required = false)
    private Collection<CommandHandler> commandHandlers = new ArrayList<>();

    @Autowired
    private QueryHandlerFactory queryHandlerFactory;

    @Autowired
    private CommandHandlerFactory commandHandlerFactory;

    @PostConstruct
    protected void initializeHandlerFactories() {
        queryHandlers.forEach( e-> {
            queryHandlerFactory.addQueryHandler(e, (Class<Query<?>>) e.getQueryType());
        });

        commandHandlers.forEach( e-> {
            commandHandlerFactory.addCommandHandler(e, (Class<Command<?>>) e.getCommandType());
        });
    }

    @Bean
    public QueryExecutor getQueryExecutor() {
        return new QueryExecutorImpl(queryHandlerFactory);
    }

    @Bean
    public CommandExecutor getCommandExecutor() {
        return new CommandExecutorImpl(commandHandlerFactory);
    }
}
