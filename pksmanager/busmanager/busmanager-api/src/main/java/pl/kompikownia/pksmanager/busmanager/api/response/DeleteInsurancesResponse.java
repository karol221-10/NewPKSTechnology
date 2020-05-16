package pl.kompikownia.pksmanager.busmanager.api.response;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kompikownia.pksmanager.busmanager.business.query.DeleteInsurancesQuery;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

@Controller
@AllArgsConstructor
public class DeleteInsurancesResponse {

    private QueryExecutor queryExecutor;

    @RequestMapping(value = "api/bus/{busId}/insurances/{insuranceId}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteInsuranceById(@PathVariable("busId") Long busId, @PathVariable("insuranceId") Long insuranceId){
        DeleteInsurancesQuery deleteInsurancesQuery = new DeleteInsurancesQuery(busId, insuranceId);

        queryExecutor.execute(deleteInsurancesQuery);
    }


}
