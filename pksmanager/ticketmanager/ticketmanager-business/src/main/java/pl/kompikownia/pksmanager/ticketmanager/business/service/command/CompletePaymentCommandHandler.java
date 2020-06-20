package pl.kompikownia.pksmanager.ticketmanager.business.service.command;

import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.ticketmanager.business.command.CompletePaymentCommand;
import pl.kompikownia.pksmanager.ticketmanager.business.integration.PaymentSystem;
import pl.kompikownia.pksmanager.ticketmanager.business.integration.QRCodeProvider;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.CompletedPaymentProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.query.GetTicketProposalQuery;
import pl.kompikownia.pksmanager.ticketmanager.business.repository.TicketRepository;

@Handler
@AllArgsConstructor
public class CompletePaymentCommandHandler extends CommandHandler<CompletedPaymentProjection, CompletePaymentCommand> {

    private PaymentSystem paymentSystem;

    private QRCodeProvider qrCodeProvider;

    private TicketRepository ticketRepository;

    @Override
    public CompletedPaymentProjection handle(CompletePaymentCommand command) {
        String ticketId = paymentSystem.completePayment(command.getPaymentId(), command.getPayerId());
        if(ticketId != null) {
            val ticket = ticketRepository.saveTicketAfterPayment(ticketId, command.getPaymentId(), command.getPayerId());
            var qrCode = qrCodeProvider.generateQrCode(ticket.getId().toString());
            return CompletedPaymentProjection.builder()
                    .qrCode(qrCode)
                    .build();
        }
        throw new RuntimeException("General problem");
    }
}
