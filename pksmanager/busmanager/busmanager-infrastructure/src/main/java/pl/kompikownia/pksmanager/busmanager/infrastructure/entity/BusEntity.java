package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;


import lombok.*;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.BusColumnNames;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = BusColumnNames.TABLE_NAME)
public class BusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = BusColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = BusColumnNames.COLUMN_MODEL)
    private String model;

    @Column(name = BusColumnNames.COLUMN_INSURANCE_ID)
    private String insuranceId;

    @Column(name = BusColumnNames.COLUMN_REGISTRATION_NUMBER)
    private String registrationNumber;

    @OneToMany(mappedBy = "bus")
    private BusFuelEntity busFuelEntity;

    @OneToMany(mappedBy = "bus")
    private BusInspectionEntity busInspectionEntity;

    @OneToMany(mappedBy = "bus")
    private BusInsuranceEntity busInsuranceEntity;

}
