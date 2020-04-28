package pl.kompikownia.pksmanager.busmanager.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.query.GetBusListQuery;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Handler
public class GetBusListQueryHandler extends QueryHandler<List<BusProjection>, GetBusListQuery> {

    BusRepositoryImpl repository;

    @Override
    public List<BusProjection> handle(GetBusListQuery query) {
    return null;
    }
}
