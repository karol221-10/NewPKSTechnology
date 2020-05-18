package pl.kompikownia.pksmanager.schedulemanager.business.application.projection;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TownProjection {
    private String townId;
    private String townName;
}
