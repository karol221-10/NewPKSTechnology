package pl.kompikownia.pksmanager.timetable.infrastructure.entity;


import lombok.*;
import pl.kompikownia.pksmanager.timetable.business.projection.ScheduleProjection;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public static ScheduleEntity of(ScheduleProjection projection) {
        return ScheduleEntity.builder()
                .id(projection.getId())
                .busId(projection.getBusId())
                .workerId(projection.getWorkerId())
                .isActive(projection.isActive())
                .price(projection.getPrice())
                .build();
    }

}
