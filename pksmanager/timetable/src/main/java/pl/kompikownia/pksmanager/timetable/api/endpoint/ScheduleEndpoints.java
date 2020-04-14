package pl.kompikownia.pksmanager.timetable.api.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.timetable.api.response.ScheduleView;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetScheduleListQuery;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
public class ScheduleEndpoints {

    private QueryExecutor queryExecutor;

    @GetMapping("/api/schedule")
    List<String> getScheduleForTowns(@RequestParam Long id1,@RequestParam Long id2){
        GetScheduleListQuery getScheduleListQuery = GetScheduleListQuery.builder()
                .id1(id1)
                .id2(id2)
                .build();

        List<ScheduleView> result = queryExecutor.execute(getScheduleListQuery);

        return Collections.singletonList(result.toString());
    }
}
