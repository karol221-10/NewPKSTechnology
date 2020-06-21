package pl.kompikownia.pksmanager.schedulemanager.api.endpoint;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.schedulemanager.api.mapper.BusStopMapper;
import pl.kompikownia.pksmanager.schedulemanager.api.mapper.GetScheduleListResponseMapper;
import pl.kompikownia.pksmanager.schedulemanager.api.request.AddNewBusStopRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.request.AddNewScheduleRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.request.UpdateBusStopRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.request.UpdateScheduleRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.response.*;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.*;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetAllSchedulesQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetDistanceBetweenTownsQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetScheduleListWithTownsQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetTownListQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Town;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
public class ScheduleEndpoint {

    private QueryExecutor queryExecutor;

    private CommandExecutor commandExecutor;

    @AnonymousAccess
    @GetMapping(value = "/api/schedule", params = {"sourceTownId", "destinationTownId"})
    public GetScheduleListResponse getScheduleForTowns(@RequestParam Long sourceTownId, @RequestParam Long destinationTownId) {
        GetScheduleListWithTownsQuery getScheduleListWithTownsQuery = GetScheduleListWithTownsQuery.builder()
                .sourceTownId(sourceTownId)
                .destinationTownId(destinationTownId)
                .build();

        return GetScheduleListResponseMapper.map(queryExecutor.execute(getScheduleListWithTownsQuery));
    }

    @GetMapping("/api/schedule")
    public GetScheduleListResponse getAllSchedules() {
        return GetScheduleListResponseMapper.map(queryExecutor.execute(new GetAllSchedulesQuery()));
    }

    @AnonymousAccess
    @GetMapping("/api/town")
    public List<Town> getAllTowns() {
        GetTownListQuery getTownListQuery = new GetTownListQuery();
        return queryExecutor.execute(getTownListQuery);
    }

    @AnonymousAccess
    @GetMapping("/api/town/{id}")
    public Town getTownById(@PathVariable String id) {
        return queryExecutor.execute(new GetTownListQuery()).stream()
                .filter(town -> town.getId().equals(Long.parseLong(id)))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/api/town")
    public Town addNewTown(@RequestBody Town town) {
        val command = AddTownCommand.builder()
                .townName(town.getName())
                .build();
        return commandExecutor.execute(command);

    }

    @PostMapping("/api/schedule")
    public AddNewScheduleResponse addNewSchedule(@RequestBody AddNewScheduleRequest request) {
        val command = AddNewScheduleCommand.builder()
                .busId(request.getBusId())
                .workerId(request.getWorkerId())
                .build();

        val result = commandExecutor.execute(command);
        return AddNewScheduleResponse.builder()
                .scheduleId(result.getId())
                .workerId(result.getWorkerId())
                .busId(result.getBusId())
                .build();
    }

    @PostMapping("/api/schedule/{scheduleId}/busstops")
    public AddNewBusStopResponse addNewBusStop(@PathVariable String scheduleId, @RequestBody AddNewBusStopRequest request) {
        val command = AddBusStopCommand.builder()
                .arrivalDate(request.getArrivalDate())
                .departureDate(request.getDepartureDate())
                .scheduleId(scheduleId)
                .price(request.getPrice())
                .townId(request.getTownId())
                .build();
        val result = commandExecutor.execute(command);
        return AddNewBusStopResponse.builder()
                .id(result.getId())
                .arrivalDate(result.getArrivalDate())
                .departureDate(result.getDepartureDate())
                .scheduleId(result.getScheduleId())
                .townId(result.getTownId())
                .price(result.getPrice())
                .distanceFromPrev(result.getDistanceFromPrev())
                .build();
    }

    @PutMapping("/api/schedule/{scheduleId}/busstops/{busStopId}")
    public UpdateBusStopResponse updateBusStop(@PathVariable String scheduleId, @PathVariable String busStopId,
                                               @RequestBody UpdateBusStopRequest request) {
        val command = UpdateBusStopCommand.builder()
                .id(busStopId)
                .scheduleId(scheduleId)
                .arrivalDate(request.getArrivalDate())
                .departureDate(request.getDepartureDate())
                .townId(request.getTownId())
                .build();
        val result = commandExecutor.execute(command);
        return UpdateBusStopResponse.builder()
                .id(result.getId())
                .arrivalDate(result.getArrivalDate())
                .departureDate(result.getDepartureDate())
                .townId(Long.parseLong(result.getTownId()))
                .price(result.getPrice())
                .distanceFromPrev(result.getDistanceFromPrev())
                .build();
    }

    @DeleteMapping("/api/schedule/{scheduleId}/busstops/{busStopId}")
    public void deleteBusStop(@PathVariable String scheduleId, @PathVariable String busStopId) {
        val command = DeleteBusStopCommand.builder()
                .busStopId(busStopId)
                .build();
        commandExecutor.execute(command);
    }

    @DeleteMapping("/api/schedule/{scheduleId}")
    public void deleteSchedule(@PathVariable String scheduleId) {
        val command = DeleteScheduleCommand.builder()
                .scheduleId(scheduleId)
                .build();
        commandExecutor.execute(command);
    }

    @PutMapping("/api/schedule/{scheduleId}")
    public UpdateScheduleResponse updateSchedule(@PathVariable String scheduleId, @RequestBody UpdateScheduleRequest request) {
        val command = UpdateScheduleCommand.builder()
                .id(scheduleId)
                .busId(request.getBusId())
                .workerId(request.getWorkerId())
                .isActive(request.getActive())
                .build();
        val result = commandExecutor.execute(command);
        return UpdateScheduleResponse.builder()
                .id(result.getId())
                .busId(result.getBusId())
                .workerId(result.getWorkerId())
                .isActive(result.isActive())
                .build();
    }

    @GetMapping(value = "/api/distance", params = {"sourceTownId", "destinationTownId"})
    public GetDistanceBetweenTownsResponse getDistanceBetweenTowns(@RequestParam String sourceTownId, @RequestParam String destinationTownId) {
        val result = queryExecutor.execute(GetDistanceBetweenTownsQuery.builder()
                        .sourceTownId(sourceTownId)
                        .destinationTownId(destinationTownId)
                        .build());
        return GetDistanceBetweenTownsResponse.builder()
                .sourceTownId(result.getSourceTownId())
                .destinationTownId(result.getDestinationTownId())
                .distance(result.getDistance())
                .build();
    }
}