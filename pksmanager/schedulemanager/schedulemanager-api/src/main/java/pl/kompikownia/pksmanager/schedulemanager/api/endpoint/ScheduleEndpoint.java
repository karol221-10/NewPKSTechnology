package pl.kompikownia.pksmanager.schedulemanager.api.endpoint;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.schedulemanager.api.mapper.BusStopMapper;
import pl.kompikownia.pksmanager.schedulemanager.api.mapper.GetScheduleListResponseMapper;
import pl.kompikownia.pksmanager.schedulemanager.api.request.AddNewScheduleRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.response.AddNewScheduleResponse;
import pl.kompikownia.pksmanager.schedulemanager.api.response.GetScheduleListResponse;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.AddNewScheduleCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetScheduleListQuery;

import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
public class ScheduleEndpoint {

    private QueryExecutor queryExecutor;

    private CommandExecutor commandExecutor;

    @GetMapping("/api/schedule")
    public GetScheduleListResponse getScheduleForTowns(@RequestParam Long sourceTownId, @RequestParam Long destinationTownId){
        GetScheduleListQuery getScheduleListQuery = GetScheduleListQuery.builder()
                .sourceTownId(sourceTownId)
                .destinationTownId(destinationTownId)
                .build();

        return GetScheduleListResponseMapper.map(queryExecutor.execute(getScheduleListQuery));
    }

    @PostMapping("/api/schedule")
    public AddNewScheduleResponse addNewSchedule(@RequestBody AddNewScheduleRequest request) {
        val command = AddNewScheduleCommand.builder()
                .busId(request.getBusId())
                .workerId(request.getWorkerId())
                .busStops(request.getBusStops().stream()
                .map(BusStopMapper::map)
                .collect(Collectors.toList()))
                .build();

        val result = commandExecutor.execute(command);
        return AddNewScheduleResponse.builder()
                .scheduleId(result.getId())
                .workerId(result.getWorkerId())
                .busId(result.getBusId())
                .busStops(result.getBusStops().stream()
                    .map(GetScheduleListResponseMapper::mapBusStop)
                    .collect(Collectors.toList()))
                .build();
    }
}
