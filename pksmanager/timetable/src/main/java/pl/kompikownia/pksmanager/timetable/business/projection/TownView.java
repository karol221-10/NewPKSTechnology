package pl.kompikownia.pksmanager.timetable.business.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(staticName = "of")
@ToString
public class TownView {

    private Long id;

    private String name;
}
