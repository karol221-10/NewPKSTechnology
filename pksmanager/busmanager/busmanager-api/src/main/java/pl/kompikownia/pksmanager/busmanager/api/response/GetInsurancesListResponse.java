package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kompikownia.pksmanager.busmanager.business.query.GetInsurancesListQuery;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;


import java.util.List;

@Controller
@AllArgsConstructor
public class GetInsurancesListResponse {

    private QueryExecutor queryExecutor;

    @RequestMapping(value = "api/bus/{id}/insurance",method = RequestMethod.GET)
    @ResponseBody
    List<InsurancesView> getInsurancesList(@PathVariable("id") Long id){

        GetInsurancesListQuery getInsurancesListQuery = new GetInsurancesListQuery();
        return null;

    }
}
