package pl.kompikownia.pksmanager.busmanager.business.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Command;

@Getter
@Builder
@AllArgsConstructor
public class DeleteInspectionCommand implements Command<Long> {
    private Long busId;
    private Long inspectionId;

}
