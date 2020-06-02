package pl.kompikownia.pksmanager.usermanager.api.mapper;

import lombok.experimental.UtilityClass;
import lombok.val;
import pl.kompikownia.pksmanager.usermanager.api.response.GetUserListResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.ResponseUserData;
import pl.kompikownia.pksmanager.usermanager.business.projection.UserWithTypeData;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class GetUserListResponseMapper {

    public static GetUserListResponse map(List<UserWithTypeData> userData) {
        val users = userData.stream()
                .map(GetUserListResponseMapper::mapToResponseUserData)
                .collect(Collectors.toList());

        return GetUserListResponse.builder()
                .users(users)
                .build();
    }

    private static ResponseUserData mapToResponseUserData(UserWithTypeData user) {
        return ResponseUserData.builder()
                .name(user.getUserData().getName())
                .surname(user.getUserData().getSurname())
                .email(user.getUserData().getEmail())
                .login(user.getLogin())
                .isWorker(user.isWorker())
                .build();
    }
}
