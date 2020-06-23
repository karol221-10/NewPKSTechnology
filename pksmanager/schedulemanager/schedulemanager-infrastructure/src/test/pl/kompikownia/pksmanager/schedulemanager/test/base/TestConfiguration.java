package pl.kompikownia.pksmanager.schedulemanager.test.base;

import lombok.val;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.kompikownia.pksmanager.cqrs.configuration.CqrsConfiguration;
import pl.kompikownia.pksmanager.schedulemanager.business.application.TownDistanceUtil;
import pl.kompikownia.pksmanager.schedulemanager.business.application.provider.TownDistanceProvider;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.BusStopRepository;
import pl.kompikownia.pksmanager.schedulemanager.business.application.repository.TownRepository;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Configuration
@Import(CqrsConfiguration.class)
@EnableJpaRepositories(basePackages = "pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository.jpa")
@ComponentScan(basePackages = {
        "pl.kompikownia.pksmanager.schedulemanager.infrastructure.repository",
        "pl.kompikownia.pksmanager.schedulemanager.business.application.service.query",
        "pl.kompikownia.pksmanager.schedulemanager.business.application.service.command",
        "pl.kompikownia.pksmanager.schedulemanager.api.endpoint",
        "pl.kompikownia.pksmanager.cqrs.domain"
})
@EntityScan(basePackages = "pl.kompikownia.pksmanager.schedulemanager.infrastructure.entity")
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfiguration {

    @Bean
    TownDistanceProvider mockedTownDistanceProvider() {
        val townDistanceProvider = Mockito.mock(TownDistanceProvider.class);
        when(townDistanceProvider.getDistanceBetweenTowns(any(), any())).thenReturn(BigDecimal.valueOf(50));
        return townDistanceProvider;
    }

    @Bean
    TownDistanceUtil townDistanceUtil(TownDistanceProvider townDistanceProvider, BusStopRepository busStopRepository, TownRepository townRepository) {
        return new TownDistanceUtil(townDistanceProvider, busStopRepository, townRepository);
    }
}
