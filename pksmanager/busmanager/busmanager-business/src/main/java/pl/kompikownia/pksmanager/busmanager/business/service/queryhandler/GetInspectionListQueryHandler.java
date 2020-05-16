package pl.kompikownia.pksmanager.busmanager.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.busmanager.business.query.GetInspectionListQuery;
import pl.kompikownia.pksmanager.busmanager.business.repository.InspectionRepository;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

import java.util.List;


@Slf4j
@AllArgsConstructor
@Handler
public class GetInspectionListQueryHandler extends QueryHandler<List<InspectionProjection>, GetInspectionListQuery> {

    private InspectionRepository repository;

    @Override
    public List<InspectionProjection> handle(GetInspectionListQuery query) {
        return repository.findAllByBusId(query.getBusId());
    }
}
