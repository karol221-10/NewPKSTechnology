package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class BusView {
    private Long id;
    private String registrationNumber;
    private LocalDateTime insuranceExpiry;
    private LocalDateTime inspectionExpiry;
}
