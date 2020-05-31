package pl.kompikownia.pksmanager.busmanager.api.endpoint;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import pl.kompikownia.pksmanager.busmanager.api.mapper.*;
import pl.kompikownia.pksmanager.busmanager.api.response.BusView;
import pl.kompikownia.pksmanager.busmanager.api.response.InspectionResponse;
import pl.kompikownia.pksmanager.busmanager.api.response.InsurancesResponse;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.busmanager.business.query.*;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class BusManagerEndpoint {

    private QueryExecutor queryExecutor;

    private CommandExecutor commandExecutor;

    @GetMapping("/api/bus")
    public List<BusView> getBusList(){
        GetBusListQuery getBusListQuery = new GetBusListQuery();

        return queryExecutor.execute(getBusListQuery)
                .stream()
                .map(BusToBusForListViewMapper::map)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "api/bus/{id}")
    public void deleteBusById(@PathVariable("id") Long id){
        DeleteBusQuery deleteBusQuery = new DeleteBusQuery(id);

        queryExecutor.execute(deleteBusQuery);
    }

    @DeleteMapping(value = "api/bus/{busId}/inspection/{inspectionId}")
    public void deleteInspectionById(@PathVariable("busId") Long busId, @PathVariable("inspectionId") Long inspectionId){
        DeleteInspectionQuery deleteInsurancesQuery = new DeleteInspectionQuery(busId,inspectionId);

        queryExecutor.execute(deleteInsurancesQuery);
    }

    @DeleteMapping(value = "api/bus/{busId}/insurances/{insuranceId}")
    public void deleteInsuranceById(@PathVariable("busId") Long busId, @PathVariable("insuranceId") Long insuranceId){
        DeleteInsurancesQuery deleteInsurancesQuery = new DeleteInsurancesQuery(busId, insuranceId);

        queryExecutor.execute(deleteInsurancesQuery);
    }

    @GetMapping(value = "api/bus/{id}/inspection")
    public List<InspectionResponse> getInspectionList(@PathVariable("id") Long id){
        GetInspectionListQuery getInspectionListQuery = new GetInspectionListQuery(id);

        return queryExecutor.execute(getInspectionListQuery)
                .stream()
                .map(PostInspectionResponseMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "api/bus/{id}/insurance")
    public List<InsurancesResponse> getInsurancesList(@PathVariable("id") Long id){

        GetInsurancesListQuery getInsurancesListQuery = new GetInsurancesListQuery(id);

        return queryExecutor.execute(getInsurancesListQuery)
                .stream()
                .map(PostInsuranceResponseMapper::map)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/bus/{busId}/inspection")
    public InspectionResponse createNewInspection(@PathVariable String busId, @RequestBody InspectionProjection inspectionProjection){
        val result = commandExecutor.execute(PostInspectionRequestMapper.map(inspectionProjection));
        return PostInspectionResponseMapper.map(result);
    }

    @PostMapping("/api/bus/{busId}/insurance")
    public InsurancesResponse createNewInsurance(@PathVariable String busId, @RequestBody InsurancesProjection insurancesProjection){
        val result = commandExecutor.execute(PostInsurancesRequestMapper.map(insurancesProjection));
        return PostInsuranceResponseMapper.map(result);
    }
}
