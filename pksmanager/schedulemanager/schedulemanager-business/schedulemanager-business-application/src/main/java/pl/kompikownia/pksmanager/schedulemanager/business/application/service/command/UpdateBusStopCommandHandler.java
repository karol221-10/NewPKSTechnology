package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.UpdateBusStopCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;

@Handler
@AllArgsConstructor
public class UpdateBusStopCommandHandler extends CommandHandler<BusStop, UpdateBusStopCommand> {

    private BusStopRepository busStopRepository;

    @Override
    public BusStop handle(UpdateBusStopCommand command) {
        val busStop = busStopRepository.findById(command.getId());
        busStop.setArrivalDate(command.getArrivalDate());
        busStop.setDepartureDate(command.getDepartureDate());
        busStop.setTownId(Long.parseLong(command.getTownId()));
        val result = busStopRepository.update(busStop);
        return BusStop.builder()
                .id(result.getId())
                .townId(result.getTownId())
                .scheduleId(result.getScheduleId())
                .departureDate(result.getDepartureDate())
                .arrivalDate(result.getArrivalDate())
                .build();
    }
}
