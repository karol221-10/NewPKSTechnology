package pl.kompikownia.pksmanager.cqrs.domain;

import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;

@Component
public abstract class CommandHandler<T, Q extends Command<T>> {

    public abstract T handle(Q command);

    public Class<Q> getCommandType() {
        Class<Q> classType = (Class<Q>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return classType;
    }
}
