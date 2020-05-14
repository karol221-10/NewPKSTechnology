package pl.kompikownia.pksmanager.usermanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.namemapper.BusColumnNames;
import pl.kompikownia.pksmanager.usermanager.infrastructure.entity.namemapper.InspectionColumnNames;

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
@Table(name = InspectionColumnNames.TABLE_NAME)
public class InspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = InspectionColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = InspectionColumnNames.COLUMN_TYPE)
    private String type;

    @Column(name = InspectionColumnNames.COLUMN_CREATION_DATE)
    private LocalDateTime creationDate;

    @Column(name = InspectionColumnNames.COLUMN_EXPIRY_DATE)
    private LocalDateTime expiryDate;

    @Column(name = InspectionColumnNames.COLUMN_COMMENT)
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = BusColumnNames.COLUMN_BUS_ID)
    private BusEntity bus;

    public InspectionProjection toProjection(){
        return InspectionProjection.builder()
                .id(id)
                .type(type)
                .creationDate(creationDate)
                .expiryDate(expiryDate)
                .comment(comment)
                .busId(bus.getId())
                .build();
    }

    public static InspectionEntity of(EntityManager em, InspectionProjection inspectionProjection){
        return InspectionEntity.builder()
                .id(inspectionProjection.getId())
                .type(inspectionProjection.getType())
                .creationDate(inspectionProjection.getCreationDate())
                .expiryDate(inspectionProjection.getExpiryDate())
                .comment(inspectionProjection.getComment())
                .bus(em.getReference(BusEntity.class,inspectionProjection.getBusId()))
                .build();
    }

}
