package pl.kompikownia.pksmanager.schedulemanager.business.api.query;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Schedule;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class GetAllSchedulesQuery implements Query<List<Schedule>> {
}
