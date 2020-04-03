package pl.kompikownia.pksmanager.timetable.infrastructure.entity;


import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isActive;
    private Long busId;
    @Column
    @ElementCollection(targetClass = DayOfWeek.class)
    private Set<DayOfWeek> scheduleDays;





}
