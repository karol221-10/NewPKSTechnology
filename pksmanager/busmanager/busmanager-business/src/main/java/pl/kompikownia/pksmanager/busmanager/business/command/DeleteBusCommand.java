package pl.kompikownia.pksmanager.busmanager.business.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.kompikownia.pksmanager.cqrs.domain.Command;

import javax.transaction.Transactional;

@Getter
@Builder
@AllArgsConstructor
@Transactional
public class DeleteBusCommand implements Command<Long> {
    private Long busId;
}
