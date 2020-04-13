package pl.kompikownia.pksmanager.timetable.business.api.internal.query;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.timetable.api.response.ScheduleView;

import java.util.List;


@Getter
@Builder
public class GetScheduleListQuery implements Query<List<ScheduleView>> {
    private Long id1;
    private Long id2;
}
