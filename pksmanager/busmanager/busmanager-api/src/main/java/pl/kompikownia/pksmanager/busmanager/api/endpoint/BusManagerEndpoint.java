package pl.kompikownia.pksmanager.busmanager.api.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.busmanager.api.mapper.BusToBusForListViewMapper;
import pl.kompikownia.pksmanager.busmanager.api.response.BusView;
import pl.kompikownia.pksmanager.busmanager.business.query.GetBusListQuery;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class BusManagerEndpoint {

    private QueryExecutor queryExecutor;

    @GetMapping("/api/bus")
    public List<BusView> getBusList(){
        GetBusListQuery getBusListQuery = new GetBusListQuery();

        return queryExecutor.execute(getBusListQuery)
                .stream()
                .map(BusToBusForListViewMapper::map)
                .collect(Collectors.toList());
    }
}
