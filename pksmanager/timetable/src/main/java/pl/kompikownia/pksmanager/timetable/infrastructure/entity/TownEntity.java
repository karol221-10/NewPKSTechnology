package pl.kompikownia.pksmanager.timetable.infrastructure.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Town")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TownEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "town")
    private List<BusStopEntity> busStopEntities;

}
