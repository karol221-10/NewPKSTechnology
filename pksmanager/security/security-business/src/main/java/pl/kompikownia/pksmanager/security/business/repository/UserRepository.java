package pl.kompikownia.pksmanager.security.business.repository;

import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserProjection;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;
import pl.kompikownia.pksmanager.security.business.projection.NewUserData;
import pl.kompikownia.pksmanager.security.business.projection.UserWithPermissionProjection;

import java.util.List;

public interface UserRepository {

    UserProjection putNewUser(NewUserData newUserData);
    Long getUserByUsernameAndPassword(String username, String password);
    UserWithPermissionProjection getUserWithPermissionsById(Long id);
    List<UserWithLoginData> getUsersByIds(List<String> ids);
}
