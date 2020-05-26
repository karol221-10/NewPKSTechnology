package pl.kompikownia.pksmanager.schedulemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.ScheduleDto;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;

import java.util.List;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GetScheduleListResponse {
    private List<ScheduleDto> schedules;
}
