package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.namemapper.InspectionColumnNames;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = InspectionColumnNames.COLUMN_ID)
    private Long id;

    @ManyToOne
    @JoinColumn(name = InspectionColumnNames.COLUMN_BUS_ID)
    @Column(name = InspectionColumnNames.COLUMN_BUS_ID)
    private Long busId;

    @Column(name = InspectionColumnNames.COLUMN_TYPE)
    private String type;

    @Column(name = InspectionColumnNames.COLUMN_CREATION_DATE)
    private LocalDateTime creationDate;

    @Column(name = InspectionColumnNames.COLUMN_EXPIRY_DATE)
    private LocalDateTime expiryDate;

    @Column(name = InspectionColumnNames.COLUMN_COMMENT)
    private String comment;


}
