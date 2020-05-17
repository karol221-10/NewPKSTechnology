package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kompikownia.pksmanager.busmanager.business.query.DeleteInspectionQuery;
import pl.kompikownia.pksmanager.busmanager.business.query.DeleteInsurancesQuery;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

@Controller
@AllArgsConstructor
public class DeleteInspectionResponse {

    private QueryExecutor queryExecutor;

    @RequestMapping(value = "api/bus/{busId}/inspection/{inspectionId}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteInspectionById(@PathVariable("busId") Long busId, @PathVariable("inspectionId") Long inspectionId){
        DeleteInspectionQuery deleteInsurancesQuery = new DeleteInspectionQuery(busId,inspectionId);

        queryExecutor.execute(deleteInsurancesQuery);
    }

}
