package pl.kompikownia.pksmanager.schedulemanager.api.request;

import lombok.*;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateScheduleRequest {
    private String workerId;
    private String busId;
    private Boolean active;
}
