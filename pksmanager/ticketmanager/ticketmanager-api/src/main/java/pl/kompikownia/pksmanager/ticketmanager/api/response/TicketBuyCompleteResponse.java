package pl.kompikownia.pksmanager.ticketmanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TicketBuyCompleteResponse {
    private byte[] qrCode;
}
