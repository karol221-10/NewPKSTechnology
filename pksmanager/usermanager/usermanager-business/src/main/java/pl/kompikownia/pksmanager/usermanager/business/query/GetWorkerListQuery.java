package pl.kompikownia.pksmanager.usermanager.business.query;

import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;

import java.util.List;

public class GetWorkerListQuery implements Query<List<WorkerData>> {
}
