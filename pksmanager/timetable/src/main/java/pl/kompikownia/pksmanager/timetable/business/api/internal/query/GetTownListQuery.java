package pl.kompikownia.pksmanager.timetable.business.api.internal.query;

import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.timetable.api.response.TownView;

import java.util.List;

public class GetTownListQuery implements Query<List<TownView>> {
}
