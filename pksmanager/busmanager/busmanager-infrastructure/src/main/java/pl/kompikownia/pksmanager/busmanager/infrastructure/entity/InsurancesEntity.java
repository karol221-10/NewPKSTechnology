package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.BusColumnNames;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.InsurancesColumnNames;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = InsurancesColumnNames.TABLE_NAME)
public class InsurancesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = InsurancesColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = InsurancesColumnNames.COLUMN_TYPE)
    private String type;

    @Column(name = InsurancesColumnNames.COLUMN_CREATION_DATE)
    private LocalDateTime creationDate;

    @Column(name = InsurancesColumnNames.COLUMN_EXPIRY_DATE)
    private LocalDateTime expiryDate;

    @Column(name = InsurancesColumnNames.COLUMN_COMMENT)
    private String comment;

    @ManyToOne
    @JoinColumn(name = BusColumnNames.COLUMN_BUS_ID)
    private BusEntity bus;

    public InsurancesProjection toProjection(){
        return InsurancesProjection.builder()
                .id(id)
                .type(type)
                .creationDate(creationDate)
                .expiryDate(expiryDate)
                .comment(comment)
                .busId(bus.getId())
                .build();
    }

    public static InsurancesEntity of(EntityManager em, InsurancesProjection insurancesProjection){
        return InsurancesEntity.builder()
                .id(insurancesProjection.getId())
                .type(insurancesProjection.getType())
                .creationDate(insurancesProjection.getCreationDate())
                .expiryDate(insurancesProjection.getExpiryDate())
                .comment(insurancesProjection.getComment())
                .bus(em.getReference(BusEntity.class,insurancesProjection.getBusId()))
                .build();
    }

}
