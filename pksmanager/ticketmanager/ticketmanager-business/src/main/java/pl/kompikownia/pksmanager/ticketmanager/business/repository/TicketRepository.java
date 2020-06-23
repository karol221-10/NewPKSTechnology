package pl.kompikownia.pksmanager.ticketmanager.business.repository;

import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProjection;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {

    List<DiscountProjection> getAllAvailableDiscounts();

    TicketProjection saveTicketBeforePayment(TicketProjection toSave);

    TicketProjection saveTicketAfterPayment(String ticketId, String paymentId, String payerId);

    Optional<DiscountProjection> getDiscountById(String id);

    List<TicketProjection> getTicketsForUser(String userId);
}
