package pl.kompikownia.pksmanager.busmanager.api.mapper;

import pl.kompikownia.pksmanager.busmanager.api.response.InsurancesView;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;

public class InsurancesToInsurancesForListViewMapper {
    public static InsurancesView map(InsurancesProjection insurancesProjection){
        return InsurancesView.builder()
                .id(insurancesProjection.getId())
                .type(insurancesProjection.getType())
                .creationDate(insurancesProjection.getCreationDate())
                .expiryDate(insurancesProjection.getExpiryDate())
                .comment(insurancesProjection.getComment())
                .build();
    }
}
