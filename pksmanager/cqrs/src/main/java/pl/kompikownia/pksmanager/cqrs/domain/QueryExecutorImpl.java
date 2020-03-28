package pl.kompikownia.pksmanager.cqrs.domain;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

@AllArgsConstructor
public class QueryExecutorImpl implements QueryExecutor {

    private QueryHandlerFactory queryHandlerFactory;

    @Override
    public Object execute(Query query) {
        return queryHandlerFactory.getQueryHandlerForQuery(query).handle(query);
    }
}
