package pl.kompikownia.pksmanager.timetable.infrastructure.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long busId;
    private Long workerId;
    private boolean isActive;
    private float price;

    @OneToMany(mappedBy = "schedule")
    private List<BusStopEntity> busStopEntities;

}
