package pl.kompikownia.pksmanager.schedulemanager.business.application.service.query;

import lombok.AllArgsConstructor;
import lombok.val;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import pl.kompikownia.pksmanager.cqrs.infrastructure.Handler;
import pl.kompikownia.pksmanager.schedulemanager.business.api.query.GetDistanceBetweenTownsQuery;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.TownDistance;
import pl.kompikownia.pksmanager.schedulemanager.business.application.TownDistanceUtil;
import pl.kompikownia.pksmanager.schedulemanager.business.application.provider.TownDistanceProvider;

@Handler
@AllArgsConstructor
public class GetDistanceBetweenTownsQueryHandler extends QueryHandler<TownDistance, GetDistanceBetweenTownsQuery> {

    private TownDistanceProvider townDistanceProvider;

    private TownDistanceUtil townDistanceUtil;

    @Override
    public TownDistance handle(GetDistanceBetweenTownsQuery query) {
        val firstTown = townDistanceUtil.getTownName(Long.parseLong(query.getSourceTownId()));
        val secondTown = townDistanceUtil.getTownName(Long.parseLong(query.getDestinationTownId()));
        if(firstTown.isPresent() && secondTown.isPresent()) {
            val distance = townDistanceProvider.getDistanceBetweenTowns(firstTown.get(), secondTown.get());
            return TownDistance.builder()
                    .sourceTownId(query.getSourceTownId())
                    .destinationTownId(query.getDestinationTownId())
                    .distance(distance.doubleValue())
                    .build();
        }
        return TownDistance.builder()
                .sourceTownId(query.getSourceTownId())
                .destinationTownId(query.getDestinationTownId())
                .distance(-1.0)
                .build();
    }
}
