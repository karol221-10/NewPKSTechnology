package pl.kompikownia.pksmanager.busmanager.entity;


import lombok.*;
import pl.kompikownia.pksmanager.usermanager.infrastructure.namemapper.UserColumnNames;
import pl.kompikownia.pksmanager.usermanager.business.projection.WorkerData;
import pl.kompikownia.pksmanager.usermanager.infrastructure.namemapper.WorkerColumnNames;

import javax.persistence.*;

@Entity
@Table(name = WorkerColumnNames.TABLE_NAME)
@Builder(builderClassName = "builder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class WorkerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = WorkerColumnNames.COLUMN_ID)
    private Long id;

    @OneToOne
    @JoinColumn(name = WorkerColumnNames.COLUMN_USER_ID, referencedColumnName = UserColumnNames.COLUMN_ID)
    private UserEntity userEntity;

    @Column(name = WorkerColumnNames.COLUMN_DRIVER_LICENSE_NUMBER)
    private String driverLicenseNumber;

    @Column(name = WorkerColumnNames.COLUMN_IDNUMBER)
    private String personIdNumber;

    @Column(name = WorkerColumnNames.COLUMN_PESEL)
    private String pesel;

    public static WorkerEntity of(WorkerData workerData, EntityManager entityManager) {
        return WorkerEntity.builder()
                .userEntity(entityManager.getReference(UserEntity.class, workerData.getWorkerId()))
                .driverLicenseNumber(workerData.getDriverLicenseNumber())
                .personIdNumber(workerData.getPersonIdNumber())
                .pesel(workerData.getPesel())
                .build();
    }
}
