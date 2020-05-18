package pl.kompikownia.pksmanager.busmanager.api.response;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.busmanager.api.mapper.PostInspectionRequestMapper;
import pl.kompikownia.pksmanager.busmanager.api.mapper.PostInspectionResponseMapper;
import pl.kompikownia.pksmanager.busmanager.api.mapper.PostInsuranceResponseMapper;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.cqrs.domain.CommandExecutor;

@RestController
@RequiredArgsConstructor
public class PostInspectionResponse {

    private final CommandExecutor commandExecutor;


    @PostMapping("/api/bus/{busId}/inspection")
    public InspectionResponse createNewInspection(@PathVariable String busId, @RequestBody InspectionProjection inspectionProjection){
        val result = commandExecutor.execute(PostInspectionRequestMapper.map(inspectionProjection));
        return PostInspectionResponseMapper.map(inspectionProjection);
    }

}
