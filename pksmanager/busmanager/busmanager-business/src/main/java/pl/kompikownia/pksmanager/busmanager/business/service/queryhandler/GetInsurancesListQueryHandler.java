package pl.kompikownia.pksmanager.busmanager.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.busmanager.business.query.GetInsurancesListQuery;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Handler
public class GetInsurancesListQueryHandler extends QueryHandler<List<InsurancesProjection>, GetInsurancesListQuery> {

    private InsurancesRepository repository;

    @Override
    public List<InsurancesProjection> handle(GetInsurancesListQuery query) {
        return repository.findAllByBusId(query.getBusId());
    }
}
