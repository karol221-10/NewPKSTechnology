package pl.kompikownia.pksmanager.ticketmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProjection;
import pl.kompikownia.pksmanager.ticketmanager.infrastructure.namemapper.TicketColumnNames;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = TicketColumnNames.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TicketColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = TicketColumnNames.PAYMENT_ID_COLUMN)
    private String paymentId;

    @Column(name = TicketColumnNames.PAYER_ID_COLUMN)
    private String payerId;

    @Column(name = TicketColumnNames.COLUMN_SCHEDULE_ID)
    private String scheduleId;

    @Column(name = TicketColumnNames.BUS_STOP_START_COLUMN)
    private String busStopStartId;

    @Column(name = TicketColumnNames.BUS_STOP_END_COLUMN)
    private String busStopEndId;

    @Column(name = TicketColumnNames.PRICE_COLUMN)
    private BigDecimal price;

    @Column(name = TicketColumnNames.PRICE_AFTER_DISCOUNT_COLUMN)
    private BigDecimal priceAfterDiscount;

    @Column(name = TicketColumnNames.PAID_COLUMN)
    private Boolean paid;

    @Column(name = TicketColumnNames.CREATION_DATE_COLUMN)
    private LocalDateTime creationDate;

    @Column(name = TicketColumnNames.UPDATE_DATE_COLUMN)
    private LocalDateTime updateDate;


    @ManyToOne
    @JoinColumn(name = TicketColumnNames.DISCOUNT_ID_COLUMN)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DiscountEntity discount;

    @Column(name = TicketColumnNames.USER_ID_COLUMN)
    private String userId;

    public static TicketEntity ofNew(TicketProjection ticketProjection, EntityManager em) {
        return TicketEntity.builder()
                .id(ticketProjection.getId())
                .busStopStartId(ticketProjection.getBusStopStartId())
                .busStopEndId(ticketProjection.getBusStopEndId())
                .discount(ticketProjection.getId() != null ? em.getReference(DiscountEntity.class, Long.parseLong(ticketProjection.getDiscountId()))
                        : null)
                .scheduleId(ticketProjection.getScheduleId())
                .paid(ticketProjection.getPaid())
                .price(ticketProjection.getPrice())
                .priceAfterDiscount(ticketProjection.getPriceAfterDiscount())
                .paymentId(ticketProjection.getPaymentId())
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .payerId(ticketProjection.getPayerId())
                .build();
    }

    public TicketProjection toProjection() {
        return TicketProjection.builder()
                .id(id)
                .scheduleId(scheduleId)
                .busStopStartId(busStopStartId)
                .busStopEndId(busStopEndId)
                .price(price)
                .priceAfterDiscount(priceAfterDiscount)
                .paid(paid)
                .payerId(payerId)
                .discountId(discount != null ? discount.getId().toString() : null )
                .build();
    }

}
