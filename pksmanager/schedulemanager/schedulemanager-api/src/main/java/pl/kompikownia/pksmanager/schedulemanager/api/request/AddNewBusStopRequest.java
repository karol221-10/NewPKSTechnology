package pl.kompikownia.pksmanager.schedulemanager.api.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddNewBusStopRequest {
    private String townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
}
