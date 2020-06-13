package pl.kompikownia.pksmanager.schedulemanager.business.api.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class SimpleSchedule {
    private String id;
    private String busId;
    private String workerId;
    private boolean isActive;
}
