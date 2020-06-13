package pl.kompikownia.pksmanager.schedulemanager.api.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddNewBusStopResponse {
    private String id;
    private String townId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private String scheduleId;
    private Double price;
    private Double distanceFromPrev;
}
