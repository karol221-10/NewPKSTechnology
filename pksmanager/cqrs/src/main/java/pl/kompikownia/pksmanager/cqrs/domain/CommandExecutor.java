package pl.kompikownia.pksmanager.cqrs.domain;

public interface CommandExecutor {
    <T, Q extends Command<T>> T execute(Q q);
}
