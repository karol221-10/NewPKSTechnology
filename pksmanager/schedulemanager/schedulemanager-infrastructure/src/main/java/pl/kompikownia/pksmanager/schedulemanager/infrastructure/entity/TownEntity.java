package pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.namemapper.TownColumnNames;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = TownColumnNames.TABLE_NAME)
public class TownEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TownColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = TownColumnNames.COLUMN_NAME)
    private String name;

    @OneToMany(mappedBy = "town")
    private List<BusStopEntity> busStopEntities;

    public TownProjection toProjection(){
        return TownProjection.builder()
                .id(id)
                .townName(name)
                .busStopProjections(busStopEntities.stream()
                .map(busStop -> busStop.toProjection(id))
                .collect(Collectors.toList()))
                .build();
    }

    public static TownEntity of(EntityManager em, TownProjection projection){
        return TownEntity.builder()
                .id(projection.getId())
                .name(projection.getTownName())
                .busStopEntities(projection.getBusStopProjections()
                        .stream()
                        .map(busStopProjection -> BusStopEntity.of(em,busStopProjection))
                        .collect(Collectors.toList()))
                .build();
    }
}
