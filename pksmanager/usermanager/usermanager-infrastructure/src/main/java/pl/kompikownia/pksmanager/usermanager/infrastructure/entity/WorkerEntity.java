package pl.kompikownia.pksmanager.usermanager.infrastructure.entity;


import lombok.*;
import pl.kompikownia.pksmanager.usermanager.infrastructure.namemapper.UserColumnNames;
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
}
