package pl.kompikownia.pksmanager.busmanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.business.command.PostInsurancesCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;

@UtilityClass
public class PostInsurancesRequestMapper {

    public static PostInsurancesCommand map(InsurancesProjection request){
        return PostInsurancesCommand.builder()
                .id(request.getId())
                .type(request.getType())
                .creationDate(request.getCreationDate())
                .expiryDate(request.getExpiryDate())
                .comment(request.getComment())
                .build();
    }

}
