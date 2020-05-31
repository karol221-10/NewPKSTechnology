package pl.kompikownia.pksmanager.busmanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.business.command.PostInspectionCommand;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;

@UtilityClass
public class PostInspectionRequestMapper {

    public static PostInspectionCommand map(InspectionProjection request){
        return PostInspectionCommand.builder()
                .id(request.getId())
                .type(request.getType())
                .creationDate(request.getCreationDate())
                .expiryDate(request.getExpiryDate())
                .comment(request.getComment())
                .build();
    }

}
