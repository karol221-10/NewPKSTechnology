package pl.kompikownia.pksmanager.busmanager.api.mapper;

import pl.kompikownia.pksmanager.busmanager.api.response.BusView;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;

import java.time.LocalDateTime;

public class BusToBusForListViewMapper {
    public static BusView map(BusProjection busProjection){
        return BusView.builder()
                .id(busProjection.getId())
                .registrationNumber(busProjection.getRegistrationNumber())
                .inspectionExpiry(getInspectionExpiry(busProjection))
                .insuranceExpiry(getInsuranceExpiry(busProjection))
                .build();
    }

    private static LocalDateTime getInsuranceExpiry(BusProjection busProjection){
        return busProjection.getInsurancesProjections().stream().map(InsurancesProjection::getExpiryDate).max(LocalDateTime::compareTo)
                .orElse(null);
    }

    private static LocalDateTime getInspectionExpiry(BusProjection busProjection){
        return busProjection.getInspectionProjections().stream().map(InspectionProjection::getExpiryDate).max(LocalDateTime::compareTo)
                .orElse(null);
    }
}
