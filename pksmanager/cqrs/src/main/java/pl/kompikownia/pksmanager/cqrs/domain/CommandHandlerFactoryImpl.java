package pl.kompikownia.pksmanager.cqrs.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandHandlerFactoryImpl implements CommandHandlerFactory{

    private Map<Class<Command<?>>, CommandHandler<?, ? extends Command<?>>> commandHandlerMap = new HashMap<>();


    @Override
    public CommandHandler getCommandHandlerForQuery(Command command) {
        return commandHandlerMap.get(command.getClass());
    }

    @Override
    public void addCommandHandler(CommandHandler<?, ? extends Command> commandHandler, Class<Command<?>> command) {
        commandHandlerMap.put(command, commandHandler);
    }
}
