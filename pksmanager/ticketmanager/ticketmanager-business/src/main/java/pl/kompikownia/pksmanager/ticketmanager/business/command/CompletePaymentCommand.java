package pl.kompikownia.pksmanager.ticketmanager.business.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kompikownia.pksmanager.cqrs.domain.Command;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.CompletedPaymentProjection;

@Getter
@Builder
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor
public class CompletePaymentCommand implements Command<CompletedPaymentProjection> {

    private String paymentId;
    private String payerId;
}
