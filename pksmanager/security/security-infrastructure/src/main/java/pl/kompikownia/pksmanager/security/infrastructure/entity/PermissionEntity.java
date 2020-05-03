package pl.kompikownia.pksmanager.security.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.PermissionColumnNames;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = PermissionColumnNames.TABLE_NAME)
@Builder(builderClassName = "builder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PermissionColumnNames.COLUMN_ID)
    private Long id;

    @Column(name = PermissionColumnNames.COLUMN_NAME)
    private String permissionName;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles = new HashSet<>();
}
