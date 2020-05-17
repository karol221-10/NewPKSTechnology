package pl.kompikownia.pksmanager.usermanager.business.query;


import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserWithTypeData;

import java.util.List;

public class GetUserListQuery implements Query<List<UserWithTypeData>> {
}
