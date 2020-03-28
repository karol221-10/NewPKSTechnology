package pl.kompikownia.pksmanager.timetable.business.service.queryhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetTownListQuery;
import pl.kompikownia.pksmanager.timetable.business.projection.TownView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Handler
public class GetTownListQueryHandler extends QueryHandler<List<TownView>, GetTownListQuery> {


    @Override
    public List<TownView> handle(GetTownListQuery query) {
        System.out.println("GetTownListQueryHandler runned");
        return Collections.singletonList(TownView.of(1L,"Kielce"));
    }
}
