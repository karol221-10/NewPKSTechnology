package pl.kompikownia.pksmanager.security.infrastructure.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.security.business.internal.api.projection.UserWithLoginData;
import pl.kompikownia.pksmanager.security.infrastructure.entity.SecurityUserEntity;

@UtilityClass
public class UserWithLoginDataMapper {

    public static UserWithLoginData map(SecurityUserEntity securityUserEntity) {
        return UserWithLoginData.builder()
                .id(securityUserEntity.getId().toString())
                .login(securityUserEntity.getUsername())
                .build();
    }
}
