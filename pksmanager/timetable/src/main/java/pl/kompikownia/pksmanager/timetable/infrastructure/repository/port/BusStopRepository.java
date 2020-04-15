package pl.kompikownia.pksmanager.timetable.infrastructure.repository.port;

import pl.kompikownia.pksmanager.timetable.infrastructure.entity.BusStopEntity;

import java.util.List;

public interface BusStopRepository {

    public BusStopEntity save(BusStopEntity busStopEntity);

    public List<BusStopEntity> findAll();

    public List<BusStopEntity> findByCourseId(Long ScheduleId);

    public void deleteById(Long id);
}
