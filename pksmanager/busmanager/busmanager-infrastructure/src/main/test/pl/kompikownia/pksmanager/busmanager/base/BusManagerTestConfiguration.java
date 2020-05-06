package pl.kompikownia.pksmanager.busmanager.base;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.kompikownia.pksmanager.cqrs.configuration.CqrsConfiguration;

@Configuration
@Import(CqrsConfiguration.class)
@EnableJpaRepositories(basePackages = "pl.kompikownia.pksmanager.busmanager.infrastructure.repository.port")
@ComponentScan(basePackages = {
        "pl.kompikownia.pksmanager.busmanager.infrastructure.repository.jpa",
        "pl.kompikownia.pksmanager.busmanager.business.service.queryhandler",
        "pl.kompikownia.pksmanager.cqrs.domain"
})
@EntityScan(basePackages = "pl.kompikownia.pksmanager.busmanager.infrastructure.entity")
@EnableAutoConfiguration
@EnableTransactionManagement
public class BusManagerTestConfiguration {
}
