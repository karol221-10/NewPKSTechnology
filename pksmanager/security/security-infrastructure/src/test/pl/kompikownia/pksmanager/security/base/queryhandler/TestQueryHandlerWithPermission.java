package pl.kompikownia.pksmanager.security.base.queryhandler;

import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.RequiresPermission;

@Handler
public class TestQueryHandlerWithPermission extends QueryHandler<Boolean, TestQueryWithPermission> {

    @RequiresPermission("TEST_PERMISSION")
    @Override
    public Boolean handle(TestQueryWithPermission query) {
        return true;
    }
}
