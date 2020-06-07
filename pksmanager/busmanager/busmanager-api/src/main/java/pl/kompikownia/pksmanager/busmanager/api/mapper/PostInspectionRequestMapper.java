package pl.kompikownia.pksmanager.busmanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.api.request.SimpleInspectionRequest;
import pl.kompikownia.pksmanager.busmanager.business.command.CreateInspectionCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;

@UtilityClass
public class PostInspectionRequestMapper {

    public static CreateInspectionCommand map(String busId, SimpleInspectionRequest request){
        return CreateInspectionCommand.builder()
                .type(request.getType())
                .creationDate(request.getCreationDate())
                .expiryDate(request.getExpiryDate())
                .comment(request.getComment())
                .busId(Long.parseLong(busId))
                .build();
    }

}
