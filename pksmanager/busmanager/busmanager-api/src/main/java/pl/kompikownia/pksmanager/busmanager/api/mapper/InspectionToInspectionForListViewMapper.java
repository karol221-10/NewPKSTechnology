package pl.kompikownia.pksmanager.busmanager.api.mapper;

import pl.kompikownia.pksmanager.busmanager.api.response.InspectionView;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;

public class InspectionToInspectionForListViewMapper {
    public static InspectionView map(InspectionProjection inspectionProjection){
        return InspectionView.builder()
                .id(inspectionProjection.getId())
                .type(inspectionProjection.getType())
                .creationDate(inspectionProjection.getCreationDate())
                .expiryDate(inspectionProjection.getExpiryDate())
                .comment(inspectionProjection.getComment())
                .build();
    }
}
