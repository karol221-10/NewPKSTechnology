package pl.kompikownia.pksmanager.timetable.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class TownView {

    private Long id;
    private String name;

}
