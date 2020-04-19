package pl.kompikownia.pksmanager.timetable.business.api.internal.query;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.timetable.api.response.ScheduleForListView;
import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;

import java.util.List;


@Getter
@Builder
public class GetScheduleListQuery implements Query<List<ScheduleProjection>> {
    private Long sourceTownId;
    private Long destinationTownId;
}
