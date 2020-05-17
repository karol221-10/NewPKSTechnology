package pl.kompikownia.pksmanager.security.business.internal.api.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Query;

@Getter
@AllArgsConstructor(staticName = "of")
public class GetRoleIdByNameQuery implements Query<Long> {
    private String roleName;
}
