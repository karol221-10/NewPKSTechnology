package pl.kompikownia.pksmanager.ticketmanager.business.service.query;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.query.GetScheduleByIdInternalQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.business.internal.api.response.Schedule;
import pl.kompikownia.pksmanager.ticketmanager.business.exception.TicketManagerException;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProposal;
import pl.kompikownia.pksmanager.ticketmanager.business.query.GetTicketProposalQuery;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.IntStream;

@Handler
public class GetTicketProposalQueryHandler extends QueryHandler<TicketProposal, GetTicketProposalQuery> {

    private QueryExecutor queryExecutor;

    @Value("${pl.kompikownia.ticketmanager.priceperkm}")
    private BigDecimal pricePerKm;

    @Value("${pl.kompikownia.ticketmanager.basePrice}")
    private BigDecimal basePrice;

    public GetTicketProposalQueryHandler(@Lazy QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    @Override
    public TicketProposal handle(GetTicketProposalQuery query) {
        val schedule = queryExecutor.execute(GetScheduleByIdInternalQuery.of(query.getScheduleId()));
        val cuttedBusStopList = cutBusStopListToContainOnlyNeededTowns(query.getSourceBusStopId(), query.getDestinationBusStopId(), schedule);
        val distance = calculateDistance(cuttedBusStopList);
        val additionalPrice = sumAdditionalPrice(cuttedBusStopList);
        val price = basePrice.add(additionalPrice).add(distance.multiply(pricePerKm));
        return TicketProposal.builder()
                .arrivalDate(cuttedBusStopList.get(0).getArrivalDate())
                .departureDate(cuttedBusStopList.get(cuttedBusStopList.size()-1).getDepartureDate())
                .numberOfStations(cuttedBusStopList.size())
                .sourceTownId(cuttedBusStopList.get(0).getTownId())
                .destinationTownId(cuttedBusStopList.get(cuttedBusStopList.size()-1).getTownId())
                .totalDistance(distance.longValue())
                .totalTimeSeconds(calculateTotalTime(cuttedBusStopList))
                .price(price)
                .build();
    }

    private BigDecimal calculateDistance(List<BusStop> busStops) {
        return busStops.stream()
                .map(BusStop::getDistanceFromPrev)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal sumAdditionalPrice(List<BusStop> busStops) {
        return busStops.stream()
                .map(BusStop::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private Long calculateTotalTime(List<BusStop> busStops) {
        val minTime = busStops.stream()
                .min((busStop1, busStop2) -> busStop1.getArrivalDate().compareTo(busStop2.getDepartureDate()));
        val maxTime = busStops.stream()
                .max((busStop1, busStop2) -> busStop1.getArrivalDate().compareTo(busStop2.getDepartureDate()));
        if(minTime.isPresent() && maxTime.isPresent()) {
            return minTime.get().getArrivalDate().until(maxTime.get().getDepartureDate(), ChronoUnit.SECONDS);
        }
        return 0L;
    }

    private List<BusStop> cutBusStopListToContainOnlyNeededTowns(String startBusStopId, String endBusStopId, Schedule schedule) {
        int startIndex = getStartIndex(startBusStopId, schedule);
        int endIndex = getEndInddex(endBusStopId, schedule);
        return schedule.getBusStops().subList(startIndex, endIndex);
    }

    private int getStartIndex(String startBusStopId, Schedule schedule) {
        return IntStream.range(0, schedule.getBusStops().size())
                .filter(arrayIndex -> schedule.getBusStops().get(arrayIndex).getId().equals(startBusStopId))
                .findFirst()
                .orElseThrow(() -> new TicketManagerException("Cannot find bus stop with id "+startBusStopId + " in schedule " + schedule.getId()));
    }

    private int getEndInddex(String endBusStopId, Schedule schedule) {
        return IntStream.range(0, schedule.getBusStops().size())
                .filter(arrayIndex -> schedule.getBusStops().get(arrayIndex).getId().equals(endBusStopId))
                .findFirst()
                .orElseThrow(() -> new TicketManagerException("Cannot find bus stop with id "+endBusStopId + " in schedule " + schedule.getId()));
    }
}
