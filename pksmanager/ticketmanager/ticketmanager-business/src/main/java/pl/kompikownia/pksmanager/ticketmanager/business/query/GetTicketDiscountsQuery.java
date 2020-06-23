package pl.kompikownia.pksmanager.ticketmanager.business.query;

import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;

import java.util.List;

public class GetTicketDiscountsQuery implements Query<List<DiscountProjection>> {
}
