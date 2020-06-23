package pl.kompikownia.pksmanager.schedulemanager.business.internal.api.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Schedule {
    private Long id;
    private float price;
    private List<BusStop> busStops;
}
