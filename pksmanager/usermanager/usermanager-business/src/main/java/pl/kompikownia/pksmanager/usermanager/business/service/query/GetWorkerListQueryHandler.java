package pl.kompikownia.pksmanager.usermanager.business.service.query;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;
import pl.kompikownia.pksmanager.usermanager.business.query.GetWorkerListQuery;
import pl.kompikownia.pksmanager.usermanager.business.repository.UserRepository;

import java.util.List;

@Handler
@AllArgsConstructor
public class GetWorkerListQueryHandler extends QueryHandler<List<WorkerData>, GetWorkerListQuery> {

    private UserRepository userRepository;

    @Override
    public List<WorkerData> handle(GetWorkerListQuery query) {
        return userRepository.getWorkersList();
    }
}
