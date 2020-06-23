package pl.kompikownia.pksmanager.usermanager.api.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class ResponseUserData {
    private String id;
    private String login;
    private String name;
    private String surname;
    private String email;
    private Boolean isWorker;
}
