package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kompikownia.pksmanager.busmanager.api.mapper.PostInspectionResponseMapper;
import pl.kompikownia.pksmanager.busmanager.business.query.GetInspectionListQuery;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class GetInspectionListResponse {

    private QueryExecutor queryExecutor;

    @RequestMapping(value = "api/bus/{id}/inspection",method = RequestMethod.GET)
    @ResponseBody
    public List<InspectionResponse> getInspectionList(@PathVariable("id") Long id){
        GetInspectionListQuery getInspectionListQuery = new GetInspectionListQuery(id);

        return queryExecutor.execute(getInspectionListQuery)
                .stream()
                .map(PostInspectionResponseMapper::map)
                .collect(Collectors.toList());
    }

}
