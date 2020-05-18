package pl.kompikownia.pksmanager.schedulemanager.business.api.query;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class GetScheduleListQuery implements Query<List<Schedule>> {

    private Long sourceTownId;
    private Long destinationTownId;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
