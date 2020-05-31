package pl.kompikownia.pksmanager.busmanager.infrastructure.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.busmanager.infrastructure.entity.UserEntity;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserData;

@UtilityClass
public class UserDataMapper {

    public static UserData map(UserEntity entity) {
        return UserData.builder()
                .id(entity.getId().toString())
                .securedId(entity.getSecurityUserId())
                .email(entity.getEmail())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }
}
