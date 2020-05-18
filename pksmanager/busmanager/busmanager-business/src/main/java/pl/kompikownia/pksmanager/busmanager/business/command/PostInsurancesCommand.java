package pl.kompikownia.pksmanager.busmanager.business.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.cqrs.domain.Command;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostInsurancesCommand  implements Command<InsurancesProjection> {
    private Long id;
    private String type;
    private LocalDateTime creationDate;
    private LocalDateTime expiryDate;
    private String comment;
    private Long busId;
}
