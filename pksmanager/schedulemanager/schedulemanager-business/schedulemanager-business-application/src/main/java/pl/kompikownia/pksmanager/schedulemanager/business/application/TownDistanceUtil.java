package pl.kompikownia.pksmanager.schedulemanager.business.application;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.application.provider.TownDistanceProvider;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.TownRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Handler
public class TownDistanceUtil {

    private TownDistanceProvider townDistanceProvider;

    private BusStopRepository busStopRepository;

    private TownRepository townRepository;

    public Optional<String> getLastTownName(String scheduleId, LocalDateTime arrivalDate) {
        val busStopProjection = busStopRepository.getBusStopBeforeDateTime(Long.parseLong(scheduleId), arrivalDate);
        if (busStopProjection.isPresent()) {
            return getTownName(Long.parseLong(busStopProjection.get().getTownId()));
        }
        return Optional.empty();
    }

    public Optional<String> getTownName(Long townId) {
        val townProjection = townRepository.findById(townId);
        if(townProjection == null) return Optional.empty();
        return Optional.of(townProjection.getTownName());
    }
}
