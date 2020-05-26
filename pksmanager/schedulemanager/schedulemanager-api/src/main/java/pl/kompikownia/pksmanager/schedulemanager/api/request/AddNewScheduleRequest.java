package pl.kompikownia.pksmanager.schedulemanager.api.request;

import lombok.*;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.NewBusStopDto;

import java.util.List;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddNewScheduleRequest {
    private Long busId;
    private Long workerId;
    private List<NewBusStopDto> busStops;
}
