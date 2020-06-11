package pl.kompikownia.pksmanager.schedulemanager.business.application.repository;


import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BusStopRepository {

    BusStopProjection save(BusStopProjection busStopProjection);

    BusStopProjection update(BusStopProjection busStopProjection);

    BusStopProjection findById(String id);

    Optional<BusStopProjection> getBusStopBeforeDateTime(Long scheduleId, LocalDateTime datetime);

    List<BusStopProjection> findAll();

    List<BusStopProjection> findByCourseId(Long scheduleId);

    void deleteById(Long id);

    void deleteAll();
}
