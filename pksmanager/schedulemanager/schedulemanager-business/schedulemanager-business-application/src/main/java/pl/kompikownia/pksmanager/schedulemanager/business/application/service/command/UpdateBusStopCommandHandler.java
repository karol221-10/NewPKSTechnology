package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.UpdateBusStopCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.application.TownDistanceUtil;
import pl.kompikownia.pksmanager.schedulemanager.business.application.provider.TownDistanceProvider;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;

import java.math.BigDecimal;

@Handler
@AllArgsConstructor
public class UpdateBusStopCommandHandler extends CommandHandler<BusStop, UpdateBusStopCommand> {

    private BusStopRepository busStopRepository;

    private TownDistanceUtil townDistanceUtil;

    private TownDistanceProvider townDistanceProvider;

    @Override
    public BusStop handle(UpdateBusStopCommand command) {
        val busStop = busStopRepository.findById(command.getId());
        val lastTownName = townDistanceUtil.getLastTownName(command.getScheduleId(), command.getArrivalDate());
        val actualTownName = townDistanceUtil.getTownName(Long.parseLong(command.getTownId()));
        BigDecimal distance = BigDecimal.ZERO;
        if (lastTownName.isPresent() && actualTownName.isPresent()) {
            distance = townDistanceProvider.getDistanceBetweenTowns(lastTownName.get(), actualTownName.get());
        }
        busStop.setArrivalDate(command.getArrivalDate());
        busStop.setDepartureDate(command.getDepartureDate());
        busStop.setTownId(command.getTownId());
        busStop.setDistanceFromPrev(distance);
        busStop.setPrice(new BigDecimal(command.getPrice()));
        val result = busStopRepository.update(busStop);
        return BusStop.builder()
                .id(result.getId())
                .townId(result.getTownId())
                .scheduleId(result.getScheduleId())
                .departureDate(result.getDepartureDate())
                .arrivalDate(result.getArrivalDate())
                .price(result.getPrice().doubleValue())
                .distanceFromPrev(result.getDistanceFromPrev().doubleValue())
                .build();
    }
}
