package pl.kompikownia.pksmanager.schedulemanager.business.application.mapper;

import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;

public class BusStopProjectionMapper {

    public static BusStopProjection map(BusStop busStop) {
        return BusStopProjection.builder()
                .id(busStop.getId())
                .scheduleId(busStop.getScheduleId())
                .arrivalDate(busStop.getArrivalDate())
                .departureDate(busStop.getDepartureDate())
                .townId(busStop.getTownId())
                .build();
    }
}
