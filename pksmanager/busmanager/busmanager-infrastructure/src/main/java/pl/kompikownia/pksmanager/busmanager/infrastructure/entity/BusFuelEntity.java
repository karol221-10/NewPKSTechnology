package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.BusFuelColumnNames;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = BusFuelColumnNames.TABLE_NAME)
public class BusFuelEntity {

    @ManyToOne
    @JoinColumn(name = BusFuelColumnNames.COLUMN_FUEL_ID)
    private FuelEntity fuelEntity;

    @ManyToOne
    @JoinColumn(name = BusFuelColumnNames.COLUMN_BUS_ID)
    private BusEntity busEntity;
}
