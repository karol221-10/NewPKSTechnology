package pl.kompikownia.pksmanager.schedulemanager.business.application.mapper;

import pl.kompikownia.pksmanager.schedulemanager.business.api.response.Town;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;

public class TownMapper {
    public static Town map(TownProjection projection){
        return Town.builder()
                .id(projection.getId())
                .name(projection.getTownName())
                .build();
    }
}
