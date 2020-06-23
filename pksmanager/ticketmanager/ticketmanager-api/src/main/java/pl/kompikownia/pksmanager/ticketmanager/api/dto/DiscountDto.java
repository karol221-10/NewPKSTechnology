package pl.kompikownia.pksmanager.ticketmanager.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
public class DiscountDto {
    private String discountId;
    private String discountName;
    private BigDecimal discountValue;
}
