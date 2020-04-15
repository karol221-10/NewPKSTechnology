package pl.kompikownia.pksmanager.timetable.infrastructure.entity;


import lombok.*;
import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "BusStop")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
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

    public BusStopProjection toProjection() {
        return BusStopProjection.builder()
                .id(id)
                .scheduleId(schedule.getId())
                .townId(town.getId())
                .arrivalDate(arrivalDate)
                .departureDate(departureDate)
                .build();
    }

    public static BusStopEntity of(EntityManager em, BusStopProjection busStopProjection) {
        return BusStopEntity.builder()
                .id(busStopProjection.getId())
                .schedule(em.getReference(ScheduleEntity.class, busStopProjection.getScheduleId()))
                .town(em.getReference(TownEntity.class, busStopProjection.getTownId()))
                .build();
    }
}
