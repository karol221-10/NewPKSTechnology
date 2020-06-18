package pl.kompikownia.pksmanager.ticketmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProjection;
import pl.kompikownia.pksmanager.ticketmanager.infrastructure.namemapper.TicketColumnNames;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = TicketColumnNames.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TicketColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = TicketColumnNames.COLUMN_SCHEDULE_ID)
    private String scheduleId;

    @Column(name = TicketColumnNames.BUS_STOP_START_COLUMN)
    private String busStopStartId;

    @Column(name = TicketColumnNames.BUS_STOP_END_COLUMN)
    private String busStopEndId;

    @Column(name = TicketColumnNames.PRICE_COLUMN)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = TicketColumnNames.DISCOUNT_ID_COLUMN)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DiscountEntity discount;

    @Column(name = TicketColumnNames.USER_ID_COLUMN)
    private String userId;

    public TicketProjection toProjection() {
        return TicketProjection.builder()
                .id(id)
                .scheduleId(scheduleId)
                .busStopStartId(busStopStartId)
                .busStopEndId(busStopEndId)
                .price(price)
                .build();
    }

}
