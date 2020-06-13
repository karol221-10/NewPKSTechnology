package pl.kompikownia.pksmanager.busmanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.api.request.SimpleBusRequest;
import pl.kompikownia.pksmanager.busmanager.business.command.CreateBusCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;

@UtilityClass
public class PostBusRequestMapper {

    public static CreateBusCommand map(SimpleBusRequest request){
        return CreateBusCommand.builder()
                .model(request.getModel())
                .registrationNumber(request.getRegistrationNumber())
                .build();
    }


}
