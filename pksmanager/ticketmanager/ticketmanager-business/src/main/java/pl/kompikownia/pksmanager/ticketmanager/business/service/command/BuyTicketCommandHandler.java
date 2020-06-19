package pl.kompikownia.pksmanager.ticketmanager.business.service.command;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.cqrs.domain.CommandHandler;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.ticketmanager.business.command.BuyTicketCommand;
import pl.kompikownia.pksmanager.ticketmanager.business.integration.PaymentSystem;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.BuyedTicketProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.query.GetTicketProposalQuery;
import pl.kompikownia.pksmanager.ticketmanager.business.repository.TicketRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


@Handler
public class BuyTicketCommandHandler extends CommandHandler<BuyedTicketProjection, BuyTicketCommand> {

    private TicketRepository ticketRepository;

    private PaymentSystem paymentSystem;

    private QueryExecutor queryExecutor;

    @Value("${pl.kompikownia.ticketmanager.currency}")
    private String currency;

    public BuyTicketCommandHandler(TicketRepository ticketRepository, PaymentSystem paymentSystem, @Lazy QueryExecutor queryExecutor) {
        this.ticketRepository = ticketRepository;
        this.paymentSystem = paymentSystem;
        this.queryExecutor = queryExecutor;
    }

    @Override
    public BuyedTicketProjection handle(BuyTicketCommand command) {
        val ticketProposal = queryExecutor.execute(GetTicketProposalQuery.builder()
                .scheduleId(command.getScheduleId())
                .sourceBusStopId(command.getSourceBusStopId())
                .destinationBusStopId(command.getDestinationBusStopId())
                .build());

        BigDecimal priceAfterDiscount = ticketProposal.getPrice();

        if(command.getDiscountId() != null && !command.getDiscountId().isEmpty()) {
            val discountProjectionOptional = ticketRepository.getDiscountById(command.getDiscountId());
            if(!discountProjectionOptional.isPresent()) {
                throw new RuntimeException("Cannot get discount with specified id: " + command.getDiscountId());
            }
            val discountProjection = discountProjectionOptional.get();
            priceAfterDiscount = ticketProposal.getPrice().multiply(discountProjection.getValue());

        }
        val ticket = TicketProjection.builder()
                .busStopStartId(command.getSourceBusStopId())
                .busStopEndId(command.getDestinationBusStopId())
                .scheduleId(command.getScheduleId())
                .price(ticketProposal.getPrice())
                .priceAfterDiscount(priceAfterDiscount)
                .paid(false)
                .build();
        ticketRepository.saveTicketBeforePayment(ticket);
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat("##.00",decimalFormatSymbols);
        val result = paymentSystem.generatePayment(f.format(priceAfterDiscount), currency);
        return BuyedTicketProjection.of(result.getRedirectUrl(), result.getStatus());
    }
}
