package pl.kompikownia.pksmanager.schedulemanager.api.request;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateBusStopRequest {
    private String townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
