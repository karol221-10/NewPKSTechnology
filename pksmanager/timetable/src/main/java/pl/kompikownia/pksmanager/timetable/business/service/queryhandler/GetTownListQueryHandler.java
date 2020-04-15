package pl.kompikownia.pksmanager.timetable.business.service.queryhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.timetable.api.response.TownView;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetTownListQuery;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa.TownEntityRepositoryImpl;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Handler
@AllArgsConstructor
public class GetTownListQueryHandler extends QueryHandler<List<TownView>, GetTownListQuery> {


    TownEntityRepositoryImpl repository = new TownEntityRepositoryImpl();

    public List<TownView> convertToView(List<TownEntity> townEntities){

        List<TownView> result = townEntities.stream().map(temp ->{
          TownView obj = TownView.builder().id(temp.getId()).name(temp.getName()).build();
            return obj;
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<TownView> handle(GetTownListQuery query) {
        return convertToView(repository.findAll());
    }
}
