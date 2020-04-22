package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
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

    @OneToMany(mappedBy = "insurance")
    private BusInsuranceEntity busInsuranceEntity;
}
