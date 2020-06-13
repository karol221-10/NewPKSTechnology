package pl.kompikownia.pksmanager.schedulemanager.business.api.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.TownDistance;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDistanceBetweenTownsQuery implements Query<TownDistance> {
    private String sourceTownId;
    private String destinationTownId;
}
