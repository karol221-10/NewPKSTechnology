package pl.kompikownia.pksmanager.usermanager.business.repository;

import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedUserProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.SavedWorkerInDbProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;

import java.util.List;

public interface UserRepository {

    CreatedUserProjection saveUser(UserData userData);

    SavedWorkerInDbProjection saveWorker(WorkerData workerData);

    List<UserData> getUserList();

    List<WorkerData> getWorkersList();

    void deactivateUser(String userId);
}
