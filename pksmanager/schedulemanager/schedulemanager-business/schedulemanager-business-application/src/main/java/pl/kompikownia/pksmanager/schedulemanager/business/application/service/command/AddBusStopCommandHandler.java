package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.AddBusStopCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;

@Handler
@AllArgsConstructor
public class AddBusStopCommandHandler extends CommandHandler<BusStop, AddBusStopCommand> {

    private BusStopRepository busStopRepository;

    @Override
    public BusStop handle(AddBusStopCommand command) {
        val busStopProjection = BusStopProjection.builder()
                .arrivalDate(command.getArrivalDate())
                .departureDate(command.getDepartureDate())
                .scheduleId(Long.parseLong(command.getScheduleId()))
                .townId(Long.parseLong(command.getTownId()))
                .build();

        val result = busStopRepository.save(busStopProjection);
        return BusStop.builder()
                .id(result.getId())
                .arrivalDate(result.getArrivalDate())
                .departureDate(result.getDepartureDate())
                .scheduleId(result.getScheduleId())
                .townId(result.getTownId())
                .build();
    }
}
