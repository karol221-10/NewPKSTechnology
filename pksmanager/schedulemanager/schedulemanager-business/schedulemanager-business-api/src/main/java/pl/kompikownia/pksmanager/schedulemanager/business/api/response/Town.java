package pl.kompikownia.pksmanager.schedulemanager.business.api.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Town {
    private Long id;
    private String name;
}
