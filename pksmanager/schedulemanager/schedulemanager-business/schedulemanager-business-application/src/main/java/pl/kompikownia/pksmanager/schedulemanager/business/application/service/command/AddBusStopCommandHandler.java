package pl.kompikownia.pksmanager.schedulemanager.business.application.service.command;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.AddBusStopCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.application.TownDistanceUtil;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;
import pl.kompikownia.pksmanager.schedulemanager.business.application.provider.TownDistanceProvider;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.TownRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Handler
@AllArgsConstructor
public class AddBusStopCommandHandler extends CommandHandler<BusStop, AddBusStopCommand> {

    private BusStopRepository busStopRepository;

    private TownDistanceProvider townDistanceProvider;

    private TownDistanceUtil townDistanceUtil;

    @Override
    public BusStop handle(AddBusStopCommand command) {

        val lastTownName = townDistanceUtil.getLastTownName(command.getScheduleId(), command.getArrivalDate());
        val actualTownName = townDistanceUtil.getTownName(Long.parseLong(command.getTownId()));
        BigDecimal distance = BigDecimal.ZERO;
        if (lastTownName.isPresent() && actualTownName.isPresent()) {
            distance = townDistanceProvider.getDistanceBetweenTowns(lastTownName.get(), actualTownName.get());
        }
        val busStopProjection = BusStopProjection.builder()
                .arrivalDate(command.getArrivalDate())
                .departureDate(command.getDepartureDate())
                .scheduleId(command.getScheduleId())
                .townId(command.getTownId())
                .distanceFromPrev(distance)
                .price(new BigDecimal(command.getPrice()))
                .build();

        val result = busStopRepository.save(busStopProjection);
        return BusStop.builder()
                .id(result.getId())
                .arrivalDate(result.getArrivalDate())
                .departureDate(result.getDepartureDate())
                .scheduleId(result.getScheduleId())
                .townId(result.getTownId())
                .price(result.getPrice().doubleValue())
                .distanceFromPrev(result.getDistanceFromPrev().doubleValue())
                .build();
    }
}
