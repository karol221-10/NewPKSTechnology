package pl.kompikownia.pksmanager.ticketmanager.business.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.query.GetScheduleByIdInternalQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.response.BusStop;
import pl.kompikownia.pksmanager.security.business.internal.api.query.GetAuthorizedUserDataQuery;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.query.GetTicketDiscountsQuery;
import pl.kompikownia.pksmanager.ticketmanager.business.repository.TicketRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Handler
public class GetTicketDiscountsQueryHandler extends QueryHandler<List<DiscountProjection>, GetTicketDiscountsQuery> {

    private TicketRepository ticketRepository;
    private QueryExecutor queryExecutor;

    public GetTicketDiscountsQueryHandler(TicketRepository ticketRepository, @Lazy  QueryExecutor queryExecutor) {
        this.ticketRepository = ticketRepository;
        this.queryExecutor = queryExecutor;
    }

    @Override
    public List<DiscountProjection> handle(GetTicketDiscountsQuery query) {
        val availableDiscounts = ticketRepository.getAllAvailableDiscounts();
        val authorizedUser = queryExecutor.execute(new GetAuthorizedUserDataQuery());
        if(authorizedUser.isPresent()) {
            val allTickets = ticketRepository.getTicketsForUser(authorizedUser.get().getId());
            val totalDistance = calculateTotalDistance(allTickets);
            val totalTickets = allTickets.size();
            return availableDiscounts.stream()
                    .filter(discount -> discount.getNeededCourses() <= totalTickets)
                    .filter(discount -> totalDistance.compareTo(BigDecimal.valueOf(discount.getNeededKm())) >= 0)
                    .collect(Collectors.toList());
        }
        return availableDiscounts.stream()
                .filter(discount -> !discount.isNeededLogin())
                .collect(Collectors.toList());
    }

    private BigDecimal calculateTotalDistance(List<TicketProjection> allTickets) {
        return allTickets.stream()
                .map(ticket -> {
                    val scheduleData = queryExecutor.execute(GetScheduleByIdInternalQuery.of(ticket.getScheduleId()));
                    val firstBusStopIndex = findBusStopIndex(scheduleData.getBusStops(), ticket.getBusStopStartId());
                    val lastBusStopIndex = findBusStopIndex(scheduleData.getBusStops(), ticket.getBusStopEndId());
                    val sublistedList = scheduleData.getBusStops().subList(firstBusStopIndex, lastBusStopIndex+1);
                    val distance = sublistedList.stream()
                            .map(BusStop::getDistanceFromPrev)
                            .reduce((BigDecimal::add))
                            .orElse(BigDecimal.ZERO);
                    return distance.subtract(sublistedList.get(0).getDistanceFromPrev());
                }).reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private int findBusStopIndex(List<BusStop> busStops, String id) {
        return IntStream.range(0, busStops.size())
                .filter(busStop -> busStops.get(busStop).getId().equals(id))
                .findFirst()
                .getAsInt();
    }
}
