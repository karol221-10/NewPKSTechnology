package pl.kompikownia.pksmanager.security.infrastructure.entity;

import lombok.*;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserProjection;
import pl.kompikownia.pksmanager.security.business.projection.NewUserData;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.UserColumnNames;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.UserRoleColumnNames;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<RoleEntity> roles = new HashSet<>();

    public static UserEntity of(EntityManager em,NewUserData newUserData) {
        return UserEntity.builder()
                .username(newUserData.getUsername())
                .password(newUserData.getPassword())
                .roles(newUserData.getRolesId().stream()
                    .map(roleId -> em.getReference(RoleEntity.class, roleId))
                    .collect(Collectors.toSet()))
                .build();
    }
}
