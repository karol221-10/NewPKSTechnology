package pl.kompikownia.pksmanager.busmanager.business.query;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.cqrs.domain.Query;

import java.util.List;


public class GetBusListQuery implements Query<List<BusProjection>> {
}
