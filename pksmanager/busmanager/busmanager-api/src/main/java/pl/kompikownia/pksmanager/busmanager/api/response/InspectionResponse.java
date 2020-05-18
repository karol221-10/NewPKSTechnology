package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class InspectionResponse {
    private Long id;
    private String type;
    private LocalDateTime creationDate;
    private LocalDateTime expiryDate;
    private String comment;
}
