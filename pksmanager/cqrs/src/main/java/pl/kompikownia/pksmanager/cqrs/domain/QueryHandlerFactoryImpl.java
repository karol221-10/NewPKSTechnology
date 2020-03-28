package pl.kompikownia.pksmanager.cqrs.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QueryHandlerFactoryImpl implements QueryHandlerFactory {

    private Map<Class<Query<?>>, QueryHandler<?, ? extends Query<?>>> queryHandlerMap = new HashMap<>();

    @Override
    public QueryHandler getQueryHandlerForQuery(Query query) {
        return queryHandlerMap.get(query.getClass());
    }

    @Override
    public void addQueryHandler(QueryHandler<?, ? extends Query> queryHandler, Class<Query<?>> query) {
        queryHandlerMap.put(query, queryHandler);
    }
}
