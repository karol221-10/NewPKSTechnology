package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.business.projection.FuelProjection;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FuelColumnNames.COLUMN_ID)
    private Long id;

    @ManyToOne
    @JoinColumn(name = BusColumnNames.COLUMN_BUS_ID)
    private BusEntity bus;

    @Column(name = FuelColumnNames.COLUMN_QUANTIFY)
    private Long quantify;

    @Column(name = FuelColumnNames.COLUMN_TYPE)
    private String type;

    public FuelProjection toProjection(){
        return FuelProjection.builder()
                .id(id)
                .quantify(quantify)
                .type(type)
                .busId(bus.getId())
                .build();
    }

    public static FuelEntity of(EntityManager em, FuelProjection fuelProjection){
        return FuelEntity.builder()
                .id(fuelProjection.getId())
                .quantify(fuelProjection.getQuantify())
                .type(fuelProjection.getType())
                .bus(em.getReference(BusEntity.class, fuelProjection.getBusId()))
                .build();
    }
}
