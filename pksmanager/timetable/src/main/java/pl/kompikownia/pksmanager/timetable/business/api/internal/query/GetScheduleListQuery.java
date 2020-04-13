package pl.kompikownia.pksmanager.timetable.business.api.internal.query;

import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.timetable.api.response.ScheduleView;

import java.util.List;

public class GetScheduleListQuery implements Query<List<ScheduleView>> {
}
