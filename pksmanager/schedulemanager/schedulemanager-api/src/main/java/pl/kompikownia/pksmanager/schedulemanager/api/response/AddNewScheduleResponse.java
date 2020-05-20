package pl.kompikownia.pksmanager.schedulemanager.api.response;

import lombok.*;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.BusStopDto;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddNewScheduleResponse {
    private long scheduleId;
    private long workerId;
    private long busId;
    private List<BusStopDto> busStops;
}
