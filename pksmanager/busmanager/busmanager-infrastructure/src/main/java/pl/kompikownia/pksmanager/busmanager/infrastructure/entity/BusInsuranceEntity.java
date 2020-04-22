package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.BusInsuranceColumnNames;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = BusInsuranceColumnNames.TABLE_NAME)
public class BusInsuranceEntity {



    @ManyToOne
    @JoinColumn(name = BusInsuranceColumnNames.COLUMN_BUS_ID)
    private BusEntity busEntity;

    @ManyToOne
    @JoinColumn(name = BusInsuranceColumnNames.COLUMN_INSURANCE_ID)
    private InsurancesEntity insurancesEntity;

}
