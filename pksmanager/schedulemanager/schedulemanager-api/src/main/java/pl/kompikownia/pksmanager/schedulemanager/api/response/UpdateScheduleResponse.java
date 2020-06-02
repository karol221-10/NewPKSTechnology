package pl.kompikownia.pksmanager.schedulemanager.api.response;


import lombok.*;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UpdateScheduleResponse {
    private String id;
    private String busId;
    private String workerId;
    private Boolean isActive;
}
