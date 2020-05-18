package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.busmanager.api.mapper.PostInsuranceResponseMapper;
import pl.kompikownia.pksmanager.busmanager.api.mapper.PostInsurancesRequestMapper;
import pl.kompikownia.pksmanager.busmanager.api.request.PostInsurancesRequest;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

@RestController
@RequiredArgsConstructor
public class PostInsurancesResponse {
    private final CommandExecutor commandExecutor;

    private final QueryExecutor queryExecutor;

    @PostMapping("/api/bus/{busId}/insurance")
    public InsurancesResponse createNewInsurance(@PathVariable String busId, @RequestBody InsurancesProjection insurancesProjection){
        val result = commandExecutor.execute(PostInsurancesRequestMapper.map(insurancesProjection));
        return PostInsuranceResponseMapper.map(insurancesProjection);
    }

}
