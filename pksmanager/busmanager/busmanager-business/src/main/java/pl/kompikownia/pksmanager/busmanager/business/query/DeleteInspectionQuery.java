package pl.kompikownia.pksmanager.busmanager.business.query;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Query;

@Getter
@Builder
@AllArgsConstructor
public class DeleteInspectionQuery implements Query<Long> {
    private Long busId;
    private Long inspectionId;
}
