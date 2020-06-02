package pl.kompikownia.pksmanager.schedulemanager.api.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UpdateBusStopResponse {
    private String id;
    private Long townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
