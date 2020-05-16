package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kompikownia.pksmanager.busmanager.business.query.DeleteBusQuery;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

@Controller
@AllArgsConstructor
public class DeleteBusResponse {

    private QueryExecutor queryExecutor;

    @RequestMapping(value = "api/bus/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteBusById(@PathVariable("id") Long id){
        DeleteBusQuery deleteBusQuery = new DeleteBusQuery(id);

        queryExecutor.execute(deleteBusQuery);
    }

}
