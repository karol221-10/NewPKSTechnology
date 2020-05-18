package pl.kompikownia.pksmanager.busmanager.api.mapper;

import pl.kompikownia.pksmanager.busmanager.api.response.InsurancesResponse;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;

public class PostInsuranceResponseMapper {
    public static InsurancesResponse map(InsurancesProjection insurancesProjection){
        return InsurancesResponse.builder()
                .id(insurancesProjection.getId())
                .type(insurancesProjection.getType())
                .creationDate(insurancesProjection.getCreationDate())
                .expiryDate(insurancesProjection.getExpiryDate())
                .comment(insurancesProjection.getComment())
                .build();
    }
}
