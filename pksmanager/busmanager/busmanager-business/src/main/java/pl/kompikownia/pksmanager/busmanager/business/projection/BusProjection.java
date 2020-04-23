package pl.kompikownia.pksmanager.busmanager.business.projection;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Setter
public class BusProjection {

    private Long id;
    private String model;
    private String registrationNumber;
    private List<FuelProjection> fuelEntity;
    private List<InspectionProjection> inspectionEntity;
    private List<InsurancesProjection> insurancesEntities;

}
