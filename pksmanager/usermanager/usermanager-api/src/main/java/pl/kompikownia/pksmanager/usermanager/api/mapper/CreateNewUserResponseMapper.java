package pl.kompikownia.pksmanager.usermanager.api.mapper;

import lombok.experimental.UtilityClass;
import pl.kompikownia.pksmanager.usermanager.api.response.CreateNewUserResponse;
import pl.kompikownia.pksmanager.usermanager.business.projection.CreatedUserProjection;

@UtilityClass
public class CreateNewUserResponseMapper {

    public static CreateNewUserResponse map(CreatedUserProjection projection) {
        return CreateNewUserResponse.builder()
                .id(projection.getId())
                .email(projection.getEmail())
                .name(projection.getName())
                .surname(projection.getSurname())
                .securityUserId(projection.getSecurityUserId())
                .build();
    }
}
