package pl.kompikownia.pksmanager.schedulemanager.business.application.projection;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TownProjection {
    private Long id;
    private String townName;
    private List<BusStopProjection> busStopProjections;
}
