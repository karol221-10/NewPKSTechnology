package pl.kompikownia.pksmanager.busmanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.busmanager.infrastructure.namemapper.UserColumnNames;

import javax.persistence.*;

@Entity
@Table(name = UserColumnNames.TABLE_NAME)
@Builder(builderClassName = "builder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UserColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = UserColumnNames.COLUMN_SECURITY_USER_ID)
    private Long securityUserId;

    @Column(name = UserColumnNames.COLUMN_NAME)
    private String name;

    @Column(name = UserColumnNames.COLUMN_SURNAME)
    private String surname;

    @Column(name = UserColumnNames.COLUMN_EMAIL)
    private String email;
}
