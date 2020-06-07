package pl.kompikownia.pksmanager.schedulemanager.business.application.mapper;

import pl.kompikownia.pksmanager.schedulemanager.business.api.command.AddTownCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.TownProjection;

import java.util.stream.Collectors;

public class TownProjectionMapper {
    public static TownProjection map(AddTownCommand town){
        return TownProjection.builder()
                .id(town.getId())
                .townName(town.getTownName())
                .busStopProjections(town.getBusStops().stream()
                        .map(BusStopProjectionMapper::map)
                        .collect(Collectors.toList()))
                .build();
    }
}
