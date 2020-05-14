package pl.kompikownia.pksmanager.usermanager.business.repository;

import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedUserProjection;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;

public interface UserRepository {

    CreatedUserProjection saveUser(UserData userData);
}
