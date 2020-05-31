package pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.namemapper.TownColumnNames;

import javax.persistence.*;
import java.util.List;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TownColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = TownColumnNames.COLUMN_NAME)
    private String name;

    @OneToMany(mappedBy = "town")
    private List<BusStopEntity> busStopEntities;

}
