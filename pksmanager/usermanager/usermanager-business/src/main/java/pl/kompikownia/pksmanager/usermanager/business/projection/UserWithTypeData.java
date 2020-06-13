package pl.kompikownia.pksmanager.usermanager.business.projection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserWithTypeData {
    private UserData userData;
    private String login;
    private boolean isWorker;
}
