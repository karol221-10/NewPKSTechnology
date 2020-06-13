package pl.kompikownia.pksmanager.cqrs.domain;

public interface QueryHandlerFactory {
    QueryHandler getQueryHandlerForQuery(Query query);

    void addQueryHandler(QueryHandler<?, ? extends Query> queryHandler, Class<Query<?>> query);
}
