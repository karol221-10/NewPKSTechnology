package pl.kompikownia.pksmanager.schedulemanager.business.application.mapper;

import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Town;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;

public class TownProjectionMapper {
    public static TownProjection map(Town town){
        return TownProjection.builder()
                .id(town.getId())
                .townName(town.getName())
                .build();
    }
}
