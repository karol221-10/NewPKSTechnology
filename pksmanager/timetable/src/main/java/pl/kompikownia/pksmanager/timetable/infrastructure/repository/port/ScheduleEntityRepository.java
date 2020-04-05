package pl.kompikownia.pksmanager.timetable.infrastructure.repository.port;

import pl.kompikownia.pksmanager.timetable.infrastructure.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleEntityRepository {

    public ScheduleEntity save(ScheduleEntity scheduleEntity);

    public List<ScheduleEntity> findCoursebyTownId(Long id1, Long id2);

    public List<ScheduleEntity> findAll();

    public List<ScheduleEntity> findById(Long id);

    public void deleteById(Long id);

}
