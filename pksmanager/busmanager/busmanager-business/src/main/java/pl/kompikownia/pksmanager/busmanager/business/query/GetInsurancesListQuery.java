package pl.kompikownia.pksmanager.busmanager.business.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.cqrs.domain.Query;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetInsurancesListQuery implements Query<List<InsurancesProjection>> {
    private Long busId;
}
