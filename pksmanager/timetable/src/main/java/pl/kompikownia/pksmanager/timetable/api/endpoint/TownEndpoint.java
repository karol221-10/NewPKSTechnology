package pl.kompikownia.pksmanager.timetable.api.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetTownListQuery;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.SecondQuery;
import pl.kompikownia.pksmanager.timetable.business.projection.TownView;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class TownEndpoint {

    private QueryExecutor queryExecutor;

    @GetMapping("/town")
    List<String> getTownList() {
        GetTownListQuery getTownListQuery = new GetTownListQuery();
        SecondQuery query = new SecondQuery();

        List<TownView> result = queryExecutor.execute(getTownListQuery);
        queryExecutor.execute(query);

        System.out.println(result.toString());

        return new ArrayList<>();
    }
}
