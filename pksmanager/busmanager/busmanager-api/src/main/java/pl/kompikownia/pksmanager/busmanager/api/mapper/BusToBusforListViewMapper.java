package pl.kompikownia.pksmanager.busmanager.api.mapper;

import jdk.vm.ci.meta.Local;
import pl.kompikownia.pksmanager.busmanager.api.response.BusView;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;

import java.time.LocalDateTime;

public class BusToBusforListViewMapper {
    public static BusView map(BusProjection busProjection){
        return BusView.builder()
                .id(busProjection.getId())
                .registrationNumber(busProjection.getRegistrationNumber())
                .inspectionExpiry(getInspectionExpiry(busProjection))
                .insuranceExpiry(getInsuranceExpiry(busProjection))
                .build();
    }

    private static LocalDateTime getInsuranceExpiry(BusProjection busProjection){
        return busProjection.getInsurancesProjections().get(busProjection.getInsurancesProjections().size()-1).getExpiryDate();
    }

    private static LocalDateTime getInspectionExpiry(BusProjection busProjection){
        return busProjection.getInspectionProjections().get(busProjection.getInspectionProjections().size()-1).getExpiryDate();
    }
}
