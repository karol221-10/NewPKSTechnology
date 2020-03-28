package pl.kompikownia.pksmanager.cqrs.domain;

import lombok.val;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;

@Component
public abstract class QueryHandler<T, Q extends Query<T>> {

    public abstract T handle(Q query);

    public Class<Q> getQueryType() {
        Class<Q> classType = (Class<Q>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return classType;
    }
}
