package pl.kompikownia.pksmanager.timetable.infrastructure.entity;


import lombok.*;
import org.hibernate.annotations.Cascade;
import pl.kompikownia.pksmanager.timetable.business.projection.BusStopProjection;
import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.namemapper.BusStopColumnNames;
import pl.kompikownia.pksmanager.timetable.infrastructure.entity.namemapper.ScheduleColumnNames;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "busStopEntities"})
@ToString(exclude = { "busStopEntities"})
@Table(name = ScheduleColumnNames.TABLE_NAME)
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = ScheduleColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = ScheduleColumnNames.COLUMN_BUS_ID)
    private Long busId;

    @Column(name = ScheduleColumnNames.COLUMN_WORKER_ID)
    private Long workerId;

    @Column(name = ScheduleColumnNames.COLUMN_IS_ACTIVE)
    private boolean isActive;

    @Column(name = ScheduleColumnNames.COLUMN_PRICE)
    private float price;

    @OneToMany(mappedBy = "schedule")
    private List<BusStopEntity> busStopEntities;

    public ScheduleProjection toProjection() {
        return ScheduleProjection.builder()
                .id(id)
                .busId(busId)
                .workerId(workerId)
                .isActive(isActive)
                .price(price)
                .busStops(busStopEntities.stream()
                    .map(BusStopEntity::toProjection)
                    .collect(Collectors.toList()))
                .build();
    }

    public static ScheduleEntity of(ScheduleProjection projection, EntityManager em) {
        return ScheduleEntity.builder()
                .id(projection.getId())
                .busId(projection.getBusId())
                .workerId(projection.getWorkerId())
                .isActive(projection.isActive())
                .price(projection.getPrice())
                .busStopEntities(projection.getBusStops().stream()
                        .map(busStopProjection -> BusStopEntity.of(em, busStopProjection))
                        .collect(Collectors.toList()))
                .build();
    }
}
