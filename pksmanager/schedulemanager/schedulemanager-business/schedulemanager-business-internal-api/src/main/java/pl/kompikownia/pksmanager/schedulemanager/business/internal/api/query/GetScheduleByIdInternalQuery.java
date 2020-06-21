package pl.kompikownia.pksmanager.schedulemanager.business.internal.api.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.response.Schedule;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
public class GetScheduleByIdInternalQuery implements Query<Schedule> {
    private String scheduleId;
}
