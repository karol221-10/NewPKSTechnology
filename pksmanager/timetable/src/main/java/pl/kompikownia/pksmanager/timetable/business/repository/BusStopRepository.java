package pl.kompikownia.pksmanager.timetable.business.repository;

import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;

import java.util.List;

public interface BusStopRepository {

    BusStopProjection save(BusStopProjection busStopProjection);

    List<BusStopProjection> findAll();

    List<BusStopProjection> findByCourseId(Long scheduleId);

    void deleteById(Long id);

    void deleteAll();
}
