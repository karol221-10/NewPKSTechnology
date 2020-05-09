package pl.kompikownia.pksmanager.security.base.queryhandler;

import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.RequiresPermission;

@Handler
public class TestQueryWithInsufficientPermissionQueryHandler extends QueryHandler<Boolean, TestQueryWithInsufficientPermission> {

    @Override
    @RequiresPermission("TEST_PERMISSION2")
    public Boolean handle(TestQueryWithInsufficientPermission query) {
        return true;
    }
}
