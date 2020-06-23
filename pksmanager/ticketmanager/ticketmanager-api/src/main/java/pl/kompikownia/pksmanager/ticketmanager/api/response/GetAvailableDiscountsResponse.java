package pl.kompikownia.pksmanager.ticketmanager.api.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kompikownia.pksmanager.ticketmanager.api.dto.DiscountDto;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GetAvailableDiscountsResponse {
    List<DiscountDto> discounts;
}
