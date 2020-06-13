package pl.kompikownia.pksmanager.busmanager.business.query;

import lombok.AllArgsConstructor;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.cqrs.domain.Query;

import java.util.List;

@AllArgsConstructor
public class GetBusListQuery implements Query<List<BusProjection>> {
}
