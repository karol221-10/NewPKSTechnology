package pl.kompikownia.pksmanager.busmanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.api.request.SimpleInsuranceRequest;
import pl.kompikownia.pksmanager.busmanager.business.command.CreateInsuranceCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;

@UtilityClass
public class PostInsurancesRequestMapper {

    public static CreateInsuranceCommand map(String busId, SimpleInsuranceRequest request){
        return CreateInsuranceCommand.builder()
                .type(request.getType())
                .creationDate(request.getCreationDate())
                .expiryDate(request.getExpiryDate())
                .comment(request.getComment())
                .busId(Long.parseLong(busId))
                .build();
    }

}
