package pl.kompikownia.pksmanager.security.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.RoleColumnNames;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.RolePermissionColumnNames;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = RoleColumnNames.TABLE_NAME)
@Builder(builderClassName = "builder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = {"users"})
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = RoleColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = RoleColumnNames.COLUMN_ROLE_NAME)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    Set<UserEntity> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = RolePermissionColumnNames.TABLE_NAME,
            joinColumns = { @JoinColumn(name = RolePermissionColumnNames.COLUMN_ROLE_ID)},
            inverseJoinColumns = { @JoinColumn(name = RolePermissionColumnNames.COLUMN_PERMISSION_ID)}
            )
    private Set<PermissionEntity> permissions = new HashSet<>();
}
