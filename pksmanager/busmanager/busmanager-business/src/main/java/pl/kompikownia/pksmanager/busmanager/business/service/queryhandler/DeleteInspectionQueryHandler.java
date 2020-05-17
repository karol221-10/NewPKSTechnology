package pl.kompikownia.pksmanager.busmanager.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.query.DeleteInspectionQuery;
import pl.kompikownia.pksmanager.busmanager.business.repository.InspectionRepository;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

@Slf4j
@AllArgsConstructor
@Handler
public class DeleteInspectionQueryHandler extends QueryHandler<Long, DeleteInspectionQuery> {

    private InspectionRepository repository;

    @Override
    public Long handle(DeleteInspectionQuery query) {

        repository.deleteById(query.getInspectionId());
        return query.getInspectionId();

    }
}
