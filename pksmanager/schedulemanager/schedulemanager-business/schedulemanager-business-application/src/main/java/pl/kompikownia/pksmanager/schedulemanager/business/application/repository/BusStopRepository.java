package pl.kompikownia.pksmanager.schedulemanager.business.application.repository;


import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;

import java.util.List;

public interface BusStopRepository {

    BusStopProjection save(BusStopProjection busStopProjection);

    BusStopProjection update(BusStopProjection busStopProjection);

    BusStopProjection findById(String id);

    List<BusStopProjection> findAll();

    List<BusStopProjection> findByCourseId(Long scheduleId);

    void deleteById(Long id);

    void deleteAll();
}
