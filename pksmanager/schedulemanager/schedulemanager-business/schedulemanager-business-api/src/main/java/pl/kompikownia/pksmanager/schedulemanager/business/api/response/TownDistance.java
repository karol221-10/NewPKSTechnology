package pl.kompikownia.pksmanager.schedulemanager.business.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TownDistance {
    private String sourceTownId;
    private String destinationTownId;
    private Double distance;
}
