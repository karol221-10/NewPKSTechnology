package pl.kompikownia.pksmanager.busmanager.business.service.queryhandler;

import pl.kompikownia.pksmanager.busmanager.business.query.PostInsurancesQuery;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;

public class PostInsurancesQueryHandler extends QueryHandler<Long, PostInsurancesQuery> {

    private InsurancesRepository repository;

    @Override
    public Long handle(PostInsurancesQuery query) {
        return null;
    }
}
