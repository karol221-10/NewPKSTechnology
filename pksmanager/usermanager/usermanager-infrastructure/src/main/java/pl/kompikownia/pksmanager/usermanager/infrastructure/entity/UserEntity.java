package pl.kompikownia.pksmanager.usermanager.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.usermanager.infrastructure.namemapper.UserColumnNames;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;

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
    private String securityUserId;

    @Column(name = UserColumnNames.COLUMN_NAME)
    private String name;

    @Column(name = UserColumnNames.COLUMN_SURNAME)
    private String surname;

    @Column(name = UserColumnNames.COLUMN_EMAIL)
    private String email;

    public static UserEntity of(UserData userData) {
        return UserEntity.builder()
                .securityUserId(userData.getSecuredId())
                .email(userData.getEmail())
                .name(userData.getName())
                .surname(userData.getSurname())
                .build();
    }
}
