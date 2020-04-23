package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.BusColumnNames;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.FuelColumnNames;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = FuelColumnNames.TABLE_NAME)
public class FuelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = FuelColumnNames.COLUMN_ID)
    private Long id;

    @ManyToOne
    @JoinColumn(name = BusColumnNames.COLUMN_BUS_ID)
    private BusEntity bus;

    @Column(name = FuelColumnNames.COLUMN_QUANTIFY)
    private Long quantify;

    @Column(name = FuelColumnNames.COLUMN_TYPE)
    private String type;
}
