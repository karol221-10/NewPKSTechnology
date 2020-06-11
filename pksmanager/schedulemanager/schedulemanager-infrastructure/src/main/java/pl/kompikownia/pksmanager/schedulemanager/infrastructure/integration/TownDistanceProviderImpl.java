package pl.kompikownia.pksmanager.schedulemanager.infrastructure.integration;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;
import pl.kompikownia.pksmanager.schedulemanager.business.application.provider.TownDistanceProvider;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
public class TownDistanceProviderImpl implements TownDistanceProvider {

    @Value("${pl.kompikownia.pksmanager.schedulemanager.distanceprovider.url}")
    private String distanceServiceUrl;

    @Override
    public BigDecimal getDistanceBetweenTowns(String townNameA, String townNameB) {
        val restTemplate = new RestTemplate();
        val fullURI = distanceServiceUrl + "="
                + UriUtils.encode(townNameA,"UTF-8")
                + "|"
                + UriUtils.encode(townNameB, "UTF-8");
        val result = restTemplate.getForObject(fullURI, GetDistanceBetweenTownsResponse.class);
        if(result != null) {
            return new BigDecimal(result.getDistance());
        }
        return BigDecimal.ZERO;
    }
}
