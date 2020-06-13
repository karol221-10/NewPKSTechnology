package pl.kompikownia.pksmanager.schedulemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDistanceBetweenTownsResponse {
    private String sourceTownId;
    private String destinationTownId;
    private Double distance;
}
