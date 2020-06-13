package pl.kompikownia.pksmanager.cqrs.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandExecutorImpl implements CommandExecutor{

    private CommandHandlerFactory commandHandlerFactory;

    @Override
    public Object execute(Command command) {
        return commandHandlerFactory.getCommandHandlerForQuery(command).handle(command);
    }
}
