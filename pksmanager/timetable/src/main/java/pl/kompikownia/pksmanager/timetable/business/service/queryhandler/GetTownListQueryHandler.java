package pl.kompikownia.pksmanager.timetable.business.service.queryhandler;

import lombok.extern.slf4j.Slf4j;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.timetable.api.response.TownView;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetTownListQuery;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.TownEntity;
import pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa.TownEntityRepositoryImpl;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Handler
public class GetTownListQueryHandler extends QueryHandler<List<TownView>, GetTownListQuery> {

    @Inject
    TownEntityRepositoryImpl repository = new TownEntityRepositoryImpl();

    public List<TownView>  convertToView(List<TownEntity> townEntities){

        List<TownView> result = townEntities.stream().map(temp ->{
            TownView obj = new TownView();
            obj.setId(temp.getId());
            obj.setName(temp.getName());

            return obj;
        }).collect(Collectors.toList());


    }

    @Override
    public List<TownView> handle(GetTownListQuery query) {
        return convertToView(repository.findAll());
    }
}
