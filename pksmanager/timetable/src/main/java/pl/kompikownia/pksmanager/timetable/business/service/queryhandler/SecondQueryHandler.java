package pl.kompikownia.pksmanager.timetable.business.service.queryhandler;

import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetTownListQuery;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.SecondQuery;
import pl.kompikownia.pksmanager.timetable.business.projection.TownView;

import java.util.ArrayList;
import java.util.List;

@Handler
public class SecondQueryHandler  extends QueryHandler<List<Integer>, SecondQuery> {

    @Override
    public List<Integer> handle(SecondQuery query) {
        System.out.println("SecondQuery runned");
        return new ArrayList<>();
    }
}
