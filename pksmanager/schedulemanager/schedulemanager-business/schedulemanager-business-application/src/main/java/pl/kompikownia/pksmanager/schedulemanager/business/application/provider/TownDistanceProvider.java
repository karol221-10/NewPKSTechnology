package pl.kompikownia.pksmanager.schedulemanager.business.application.provider;

import java.math.BigDecimal;

public interface TownDistanceProvider {
    BigDecimal getDistanceBetweenTowns(String townNameA, String townNameB);
}
