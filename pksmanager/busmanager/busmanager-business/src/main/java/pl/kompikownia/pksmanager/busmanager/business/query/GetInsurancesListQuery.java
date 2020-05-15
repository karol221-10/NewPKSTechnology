package pl.kompikownia.pksmanager.busmanager.business.query;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.cqrs.domain.Query;

import java.util.List;

@Getter
@Builder
public class GetInsurancesListQuery implements Query<List<InsurancesProjection>> {
    public Long busId;
}
