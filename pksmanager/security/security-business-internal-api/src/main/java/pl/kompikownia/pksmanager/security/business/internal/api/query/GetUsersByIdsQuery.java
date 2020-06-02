package pl.kompikownia.pksmanager.security.business.internal.api.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class GetUsersByIdsQuery implements Query<List<UserWithLoginData>> {

    private List<String> ids;
}
