package pl.kompikownia.pksmanager.schedulemanager.api.mapper;

import pl.kompikownia.pksmanager.schedulemanager.api.dto.BusStopDto;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.NewBusStopDto;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;

public class BusStopMapper {

    public static BusStop map(NewBusStopDto busStopDto) {
        return BusStop.builder()
                .townId(busStopDto.getTownId().toString())
                .departureDate(busStopDto.getDepartureDate())
                .arrivalDate(busStopDto.getArrivalDate())
                .build();
    }
}
