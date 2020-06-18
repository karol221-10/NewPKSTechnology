package pl.kompikownia.pksmanager.ticketmanager.business.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@AllArgsConstructor(staticName = "of")
@Builder
public class DiscountProjection {
    private String id;
    private String name;
    private BigDecimal value;
    private boolean neededLogin;
    private int neededKm;
    private int neededCourses;
}
