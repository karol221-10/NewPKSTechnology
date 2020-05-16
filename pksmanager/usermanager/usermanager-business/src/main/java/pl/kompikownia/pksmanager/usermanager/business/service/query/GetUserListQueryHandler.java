package pl.kompikownia.pksmanager.usermanager.business.service.query;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;
import pl.kompikownia.pksmanager.security.business.internal.api.query.GetUsersByIdsQuery;
import pl.kompikownia.pksmanager.usermanager.business.mapper.UserWithTypeDataMapper;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserWithTypeData;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;
import pl.kompikownia.pksmanager.usermanager.business.query.GetUserListQuery;
import pl.kompikownia.pksmanager.usermanager.business.query.GetWorkerListQuery;
import pl.kompikownia.pksmanager.usermanager.business.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Handler
public class GetUserListQueryHandler extends QueryHandler<List<UserWithTypeData>, GetUserListQuery> {

    private UserRepository userRepository;

    private QueryExecutor queryExecutor;

    public GetUserListQueryHandler(UserRepository userRepository, @Lazy QueryExecutor queryExecutor) {
        this.userRepository = userRepository;
        this.queryExecutor = queryExecutor;
    }

    @Override
    public List<UserWithTypeData> handle(GetUserListQuery query) {
        val users = userRepository.getUserList();
        val getUsersByIdsQuery = GetUsersByIdsQuery.of(users.stream().map(UserData::getSecuredId).collect(Collectors.toList()));
        val workers = queryExecutor.execute(new GetWorkerListQuery());
        val securedQueryResult = queryExecutor.execute(getUsersByIdsQuery);

        return users.stream()
                .map(userData ->
                                UserWithTypeDataMapper.map(
                                        userData,
                                        findWorkerWithUserId(workers, userData.getId()),
                                        findUserByLoginBySecuredId(securedQueryResult, userData.getSecuredId()))
                        )
                .collect(Collectors.toList());
    }

    private WorkerData findWorkerWithUserId(List<WorkerData> workers, String userId) {
        val worker = workers.stream()
                .filter(workerData -> workerData.getUserId().toString().equals(userId))
                .findFirst();
        return worker.orElse(null);
    }

    private UserWithLoginData findUserByLoginBySecuredId(List<UserWithLoginData> securedUsers, String id) {
        return securedUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with secured id " + id + " does not exists"));
    }

}
