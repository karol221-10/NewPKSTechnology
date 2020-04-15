package pl.kompikownia.pksmanager.timetable.infrastructure.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "BusStop")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BusStopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    private ScheduleEntity schedule;

    @ManyToOne
    @JoinColumn(name = "townId",referencedColumnName = "id")
    private TownEntity town;

    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
