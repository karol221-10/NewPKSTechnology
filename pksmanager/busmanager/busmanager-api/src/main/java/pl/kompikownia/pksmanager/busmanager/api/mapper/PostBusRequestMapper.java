package pl.kompikownia.pksmanager.busmanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.business.command.PostBusCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;

@UtilityClass
public class PostBusRequestMapper {

    public static PostBusCommand map(BusProjection request){
        return PostBusCommand.builder()
                .id(request.getId())
                .model(request.getModel())
                .registrationNumber(request.getRegistrationNumber())
                .inspectionProjections(request.getInspectionProjections())
                .insurancesProjections(request.getInsurancesProjections())
                .build();
    }


}
