package pl.kompikownia.pksmanager.timetable.api.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.timetable.api.response.TownView;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetTownListQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@AllArgsConstructor
public class TownEndpoints {

    private QueryExecutor queryExecutor;

    @GetMapping("/api/locality")
    List<String> getTownList() {
        GetTownListQuery getTownListQuery = new GetTownListQuery();

        List<TownView> result = queryExecutor.execute(getTownListQuery);

        return Collections.singletonList(result.toString());
    }

}

