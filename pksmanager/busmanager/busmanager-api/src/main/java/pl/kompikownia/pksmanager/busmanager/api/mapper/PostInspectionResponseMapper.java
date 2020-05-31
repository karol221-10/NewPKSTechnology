package pl.kompikownia.pksmanager.busmanager.api.mapper;

import pl.kompikownia.pksmanager.busmanager.api.response.InspectionResponse;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;

public class PostInspectionResponseMapper {
    public static InspectionResponse map(InspectionProjection inspectionProjection){
        return InspectionResponse.builder()
                .id(inspectionProjection.getId())
                .type(inspectionProjection.getType())
                .creationDate(inspectionProjection.getCreationDate())
                .expiryDate(inspectionProjection.getExpiryDate())
                .comment(inspectionProjection.getComment())
                .build();
    }
}
