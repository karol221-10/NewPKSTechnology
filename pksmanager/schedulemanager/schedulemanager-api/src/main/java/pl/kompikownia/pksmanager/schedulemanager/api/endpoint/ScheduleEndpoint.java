package pl.kompikownia.pksmanager.schedulemanager.api.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.schedulemanager.api.mapper.GetScheduleListResponseMapper;
import pl.kompikownia.pksmanager.schedulemanager.api.response.GetScheduleListResponse;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetScheduleListQuery;


@RestController
@AllArgsConstructor
public class ScheduleEndpoint {

    private QueryExecutor queryExecutor;

    @GetMapping("/api/schedule")
    public GetScheduleListResponse getScheduleForTowns(@RequestParam Long sourceTownId, @RequestParam Long destinationTownId){
        GetScheduleListQuery getScheduleListQuery = GetScheduleListQuery.builder()
                .sourceTownId(sourceTownId)
                .destinationTownId(destinationTownId)
                .build();

        return GetScheduleListResponseMapper.map(queryExecutor.execute(getScheduleListQuery));
    }
}
