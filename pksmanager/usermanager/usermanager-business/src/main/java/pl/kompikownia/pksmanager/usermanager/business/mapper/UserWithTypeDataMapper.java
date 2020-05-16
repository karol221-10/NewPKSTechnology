package pl.kompikownia.pksmanager.usermanager.business.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserWithTypeData;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;

@UtilityClass
public class UserWithTypeDataMapper {

    public static UserWithTypeData map(UserData userData, WorkerData workerData, UserWithLoginData userWithLoginData) {
        return UserWithTypeData.builder()
                .userData(userData)
                .isWorker(workerData != null)
                .login(userWithLoginData.getLogin())
                .build();
    }
}
