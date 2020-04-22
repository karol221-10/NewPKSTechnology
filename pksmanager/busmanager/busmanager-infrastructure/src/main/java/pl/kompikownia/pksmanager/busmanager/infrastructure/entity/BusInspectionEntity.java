package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.BusInspectionColumnNames;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = BusInspectionColumnNames.TABLE_NAME)
public class BusInspectionEntity {

    @ManyToOne
    @JoinColumn(name = BusInspectionColumnNames.COLUMN_BUS_ID)
    private BusEntity busEntity;

    @ManyToOne
    @JoinColumn(name = BusInspectionColumnNames.COLUMN_INSPECTION_ID)
    private InsurancesEntity insurancesEntity;
}
