package pl.kompikownia.pksmanager.busmanager.business.command;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.cqrs.domain.Command;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostInspectionCommand implements Command<InspectionProjection> {
    private Long id;
    private String type;
    private LocalDateTime creationDate;
    private LocalDateTime expiryDate;
    private String comment;
    private Long busId;
}
