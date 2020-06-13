package pl.kompikownia.pksmanager.schedulemanager.business.application.service.query;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetTownListQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Town;
import pl.kompikownia.pksmanager.schedulemanager.business.application.mapper.TownMapper;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.TownRepository;

import java.util.List;
import java.util.stream.Collectors;

@Handler
@AllArgsConstructor
public class GetTownListQueryHandler extends QueryHandler<List<Town>, GetTownListQuery> {

    private TownRepository townRepository;

    @Override
    public List<Town> handle(GetTownListQuery query) {
        return townRepository.findAll().stream()
                .map(TownMapper::map)
                .collect(Collectors.toList());
    }
}
