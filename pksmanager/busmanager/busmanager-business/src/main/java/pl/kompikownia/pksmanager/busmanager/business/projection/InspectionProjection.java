package pl.kompikownia.pksmanager.busmanager.business.projection;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Setter
public class InspectionProjection {

    private Long id;
    private String type;
    private LocalDateTime creationDate;
    private LocalDateTime expiryDate;
    private String comment;
    private Long busId;

}
