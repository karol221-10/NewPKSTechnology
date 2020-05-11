package pl.kompikownia.pksmanager.security.base.queryhandler;

import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

@Handler
public class TestQueryWithoutPermissionQueryHandler extends QueryHandler<Boolean, TestQueryWithoutPermission> {

    @Override
    public Boolean handle(TestQueryWithoutPermission query) {
        return true;
    }
}
