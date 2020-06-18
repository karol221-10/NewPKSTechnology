package pl.kompikownia.pksmanager.ticketmanager.business.repository;

import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProjection;

import java.util.List;

public interface TicketRepository {

    List<DiscountProjection> getAllAvailableDiscounts();

    List<TicketProjection> getTicketsForUser(String userId);
}
