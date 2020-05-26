package pl.kompikownia.pksmanager.schedulemanager.business.application.mapper;

import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;

public class BusStopMapper {

    public static BusStop map(BusStopProjection busStopProjection) {
        return BusStop.builder()
                .id(busStopProjection.getId())
                .arrivalDate(busStopProjection.getArrivalDate())
                .departureDate(busStopProjection.getDepartureDate())
                .scheduleId(busStopProjection.getScheduleId())
                .townId(busStopProjection.getTownId())
                .build();
    }
}
