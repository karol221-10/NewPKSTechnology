package pl.kompikownia.pksmanager.cqrs.domain;

public interface CommandHandlerFactory {

    CommandHandler getCommandHandlerForQuery(Command command);

    void addCommandHandler(CommandHandler<?, ? extends Command> commandHandler, Class<Command<?>> command);
}
