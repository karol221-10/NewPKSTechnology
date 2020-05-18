package pl.kompikownia.pksmanager.schedulemanager.business.application.repository;


import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.ScheduleProjection;

import java.util.List;

public interface ScheduleRepository {
    ScheduleProjection save(ScheduleProjection scheduleProjection);

    List<ScheduleProjection> findCoursesByTownId(Long id1, Long id2);

    List<ScheduleProjection> findById(Long id);

    List<ScheduleProjection> findAll();

    void deleteById(Long id);

    void deleteAll();
}
