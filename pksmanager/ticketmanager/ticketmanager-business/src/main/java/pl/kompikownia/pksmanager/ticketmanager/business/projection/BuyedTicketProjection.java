package pl.kompikownia.pksmanager.ticketmanager.business.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BuyedTicketProjection {
    private String redirectUrl;
    private String status;
}
