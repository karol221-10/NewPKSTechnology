package pl.kompikownia.pksmanager.ticketmanager.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TicketBuyCompleteRequest {

    private String paymentId;
    private String payerId;
}
