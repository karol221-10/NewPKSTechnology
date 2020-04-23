package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;


import lombok.*;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.BusColumnNames;

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
@Table(name = BusColumnNames.TABLE_NAME)
public class BusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = BusColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = BusColumnNames.COLUMN_MODEL)
    private String model;

    @Column(name = BusColumnNames.COLUMN_REGISTRATION_NUMBER)
    private String registrationNumber;

    @OneToMany(mappedBy = "bus",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FuelEntity> fuelEntity;

    @OneToMany(mappedBy = "bus")
    private List<InspectionEntity> inspectionEntity;

    @OneToMany(mappedBy = "bus")
    private List<InsurancesEntity> insurancesEntities;

    public BusProjection toProjection() {
        return BusProjection.builder()
                .id(id)
                .model(model)
                .registrationNumber(registrationNumber)
                .fuelEntity(fuelEntity.stream()
                        .map(FuelEntity::toProjection)
                        .collect(Collectors.toList()))
                .inspectionEntity(inspectionEntity.stream()
                        .map(InspectionEntity::toProjection)
                        .collect(Collectors.toList()))
                .insurancesEntities(insurancesEntities.stream()
                        .map(InsurancesEntity::toProjection)
                        .collect(Collectors.toList()))
                .build();
    }

}
