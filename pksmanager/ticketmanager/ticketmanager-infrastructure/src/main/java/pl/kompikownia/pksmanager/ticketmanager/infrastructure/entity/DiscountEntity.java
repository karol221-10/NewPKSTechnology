package pl.kompikownia.pksmanager.ticketmanager.infrastructure.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.infrastructure.namemapper.DiscountColumnNames;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = DiscountColumnNames.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DiscountColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = DiscountColumnNames.COLUMN_DISCOUNT_NAME)
    private String name;

    @Column(name = DiscountColumnNames.COLUMN_DISCOUNT_PERCENT)
    private BigDecimal percent;

    @Column(name = DiscountColumnNames.COLUMN_NEEDED_LOGIN)
    private Boolean neededLogin;

    @Column(name = DiscountColumnNames.COLUMN_NEEDED_KM)
    private Integer neededKm;

    @Column(name = DiscountColumnNames.COLUMN_NEEDED_COURSES)
    private Integer neededCourses;

    @Column(name = DiscountColumnNames.COLUMN_IS_ACTIVE)
    private Boolean isActive;

    @OneToMany(mappedBy = "discount")
    private List<TicketEntity> tickets;

    public DiscountProjection toProjection() {
        return DiscountProjection.builder()
                .id(id.toString())
                .name(name)
                .value(percent)
                .neededCourses(neededCourses)
                .neededKm(neededKm)
                .neededLogin(neededLogin)
                .build();
    }
}
