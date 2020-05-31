package pl.kompikownia.pksmanager.busmanager.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.query.DeleteBusQuery;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

@Slf4j
@AllArgsConstructor
@Handler
public class DeleteBusQueryHandler extends QueryHandler<Long, DeleteBusQuery> {

    private BusRepository repository;

    @Override
    public Long handle(DeleteBusQuery query) {
        repository.deleteById(query.getBusId());
        return query.getBusId();
    }
}
