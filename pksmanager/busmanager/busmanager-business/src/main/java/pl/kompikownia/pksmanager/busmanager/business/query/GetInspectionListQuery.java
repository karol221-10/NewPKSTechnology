package pl.kompikownia.pksmanager.busmanager.business.query;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.cqrs.domain.Query;

import java.util.List;

@Getter
@Builder
public class GetInspectionListQuery implements Query<List<InspectionProjection>> {
    private Long busId;

}
