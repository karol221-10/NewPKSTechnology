package pl.kompikownia.pksmanager.timetable.api.response;


import lombok.*;


@ToString
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class TownView {

    private Long id;
    private String name;

}
