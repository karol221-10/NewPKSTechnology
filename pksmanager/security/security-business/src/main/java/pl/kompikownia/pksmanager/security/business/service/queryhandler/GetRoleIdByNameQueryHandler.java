package pl.kompikownia.pksmanager.security.business.service.queryhandler;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.security.business.internal.api.query.GetRoleIdByNameQuery;
import pl.kompikownia.pksmanager.security.business.repository.RoleRepository;

@Handler
@AllArgsConstructor
public class GetRoleIdByNameQueryHandler extends QueryHandler<Long, GetRoleIdByNameQuery> {

    private RoleRepository roleRepository;

    @Override
    public Long handle(GetRoleIdByNameQuery query) {
        return roleRepository.getRoleIdByName(query.getRoleName());
    }
}
