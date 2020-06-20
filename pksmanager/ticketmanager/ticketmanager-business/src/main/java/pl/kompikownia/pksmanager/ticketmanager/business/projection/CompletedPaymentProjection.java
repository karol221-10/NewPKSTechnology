package pl.kompikownia.pksmanager.ticketmanager.business.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor(staticName = "of")
public class CompletedPaymentProjection {
    private byte[] qrCode;
}
