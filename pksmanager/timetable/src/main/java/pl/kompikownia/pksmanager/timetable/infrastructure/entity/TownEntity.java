package pl.kompikownia.pksmanager.timetable.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Town")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TownEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "town")
    private List<BusStopEntity> busStopEntities;

}
