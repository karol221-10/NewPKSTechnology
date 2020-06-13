package pl.kompikownia.pksmanager.schedulemanager.business.api.query;

import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Town;

import java.util.List;

public class GetTownListQuery implements Query<List<Town>> {
}
