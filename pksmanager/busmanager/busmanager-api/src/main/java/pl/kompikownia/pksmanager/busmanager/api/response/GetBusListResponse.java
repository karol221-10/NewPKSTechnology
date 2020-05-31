package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kompikownia.pksmanager.busmanager.api.mapper.BusToBusForListViewMapper;
import pl.kompikownia.pksmanager.busmanager.business.query.GetBusListQuery;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class GetBusListResponse {


}
