package pl.kompikownia.pksmanager.ticketmanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
public class TicketBuyResponse {
    private String redirectUrl;
    private String status;
}
