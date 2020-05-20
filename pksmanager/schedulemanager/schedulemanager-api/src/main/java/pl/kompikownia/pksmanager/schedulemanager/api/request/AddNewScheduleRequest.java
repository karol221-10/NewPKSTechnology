package pl.kompikownia.pksmanager.schedulemanager.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.NewBusStopDto;

import java.util.List;

@NoArgsConstructor
@Getter
public class AddNewScheduleRequest {
    private Long busId;
    private Long workerId;
    private List<NewBusStopDto> busStops;
}
