package pl.kompikownia.pksmanager.timetable.business.api.internal.query;

import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.timetable.business.projection.TownView;

import java.util.List;

public class GetTownListQuery implements Query<List<TownView>> {

}
