package pl.kompikownia.pksmanager.timetable.infrastructure.entity;


import lombok.*;

import javax.persistence.*;

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
}
