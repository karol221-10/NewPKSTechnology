package pl.kompikownia.pksmanager.timetable.business.repository;

import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;

import java.util.List;

public interface ScheduleEntityRepository {
    ScheduleProjection save(ScheduleProjection scheduleProjection);

    List<ScheduleProjection> findCoursesByTownId(Long id1, Long id2);

    List<ScheduleProjection> findById(Long id);

    List<ScheduleProjection> findAll();

    void deleteById(Long id);

    void deleteAll();
}
