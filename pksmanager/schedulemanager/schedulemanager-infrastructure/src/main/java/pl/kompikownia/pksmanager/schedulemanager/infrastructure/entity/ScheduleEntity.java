package pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity;


import lombok.*;
import pl.kompikownia.pksmanager.schedulemanager.business.application.projection.ScheduleProjection;
import pl.kompikownia.pksmanager.schedulemanager.infrastructure.namemapper.ScheduleColumnNames;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "schedule", orphanRemoval = true)
    private List<BusStopEntity> busStopEntities;

    public ScheduleProjection toProjection() {
        return ScheduleProjection.builder()
                .id(id)
                .busId(busId)
                .workerId(workerId)
                .isActive(isActive)
                .price(price)
                .busStops(busStopEntities.stream()
                    .map(busStop -> busStop.toProjection(id))
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
