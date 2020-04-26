package pl.kompikownia.pksmanager.security.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.UserColumnNames;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.UserRoleColumnNames;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = UserColumnNames.TABLE_NAME)
@Builder(builderClassName = "builder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UserColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = UserColumnNames.COLUMN_USER_NAME)
    private String username;

    @Column(name = UserColumnNames.COLUMN_USER_PASSWORD)
    private String password;

    @ManyToMany
    @JoinTable(
            name = UserRoleColumnNames.TABLE_NAME,
            joinColumns = { @JoinColumn(name = UserRoleColumnNames.COLUMN_USER_ID)},
            inverseJoinColumns = { @JoinColumn(name = UserRoleColumnNames.COLUMN_ROLE_ID)}
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
