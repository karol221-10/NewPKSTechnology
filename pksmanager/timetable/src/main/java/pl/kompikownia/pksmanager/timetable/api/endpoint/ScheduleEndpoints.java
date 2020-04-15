package pl.kompikownia.pksmanager.timetable.api.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.timetable.api.mapper.ScheduleToScheduleForListViewMapper;
import pl.kompikownia.pksmanager.timetable.api.response.ScheduleForListView;
import pl.kompikownia.pksmanager.timetable.business.api.internal.query.GetScheduleListQuery;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ScheduleEndpoints {

    private QueryExecutor queryExecutor;

    @GetMapping("/api/schedule")
    List<ScheduleForListView> getScheduleForTowns(@RequestParam Long sourceTownId,@RequestParam Long destinationTownId){
        GetScheduleListQuery getScheduleListQuery = GetScheduleListQuery.builder()
                .sourceTownId(sourceTownId)
                .destinationTownId(destinationTownId)
                .build();

        return queryExecutor.execute(getScheduleListQuery)
                .stream()
                .map(ScheduleToScheduleForListViewMapper::map)
                .collect(Collectors.toList());
    }
}
