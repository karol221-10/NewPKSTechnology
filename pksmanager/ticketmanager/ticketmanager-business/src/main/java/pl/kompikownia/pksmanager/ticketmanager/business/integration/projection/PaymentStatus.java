package pl.kompikownia.pksmanager.ticketmanager.business.integration.projection;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Setter
public class PaymentStatus {
    private String status;
    private String redirectUrl;
}
