package pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity;


import lombok.*;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.BusStopProjection;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.namemapper.BusStopColumnNames;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = BusStopColumnNames.TABLE_NAME)
@Builder(builderClassName = "builder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "schedule"})
@ToString(exclude = { "schedule"})
public class BusStopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BusStopColumnNames.COLUMN_ID)
    private Long id;

    @ManyToOne
    @JoinColumn(name = BusStopColumnNames.COLUMN_SCHEDULE_ID)
    private ScheduleEntity schedule;

    @ManyToOne
    @JoinColumn(name = BusStopColumnNames.COLUMN_TOWN_ID)
    private TownEntity town;

    @Column(name = BusStopColumnNames.COLUMN_ARRIVAL_DATE)
    private LocalDateTime arrivalDate;

    @Column(name = BusStopColumnNames.COLUMN_DEPARTURE_DATE)
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
                .arrivalDate(busStopProjection.getArrivalDate())
                .departureDate(busStopProjection.getDepartureDate())
                .schedule(em.getReference(ScheduleEntity.class, busStopProjection.getScheduleId()))
                .town(em.getReference(TownEntity.class, busStopProjection.getTownId()))
                .build();
    }
}
