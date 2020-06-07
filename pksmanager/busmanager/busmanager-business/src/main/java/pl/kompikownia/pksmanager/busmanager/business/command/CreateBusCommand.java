package pl.kompikownia.pksmanager.busmanager.business.command;

import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.SimpleBusProjection;
import pl.kompikownia.pksmanager.cqrs.domain.Command;

import java.util.List;

@Getter
@Builder
public class CreateBusCommand implements Command<SimpleBusProjection> {

    private Long id;
    private String model;
    private String registrationNumber;
}
