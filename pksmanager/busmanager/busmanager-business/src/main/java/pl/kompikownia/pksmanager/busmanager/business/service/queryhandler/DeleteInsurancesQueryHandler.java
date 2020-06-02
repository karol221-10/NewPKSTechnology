package pl.kompikownia.pksmanager.busmanager.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.query.DeleteInsurancesQuery;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;


@Slf4j
@AllArgsConstructor
@Handler
public class DeleteInsurancesQueryHandler extends QueryHandler<Long, DeleteInsurancesQuery> {

    private InsurancesRepository repository;

    @Override
    public Long handle(DeleteInsurancesQuery query) {

        repository.deleteById(query.getInsuranceId());
        return query.getInsuranceId();
    }
}
