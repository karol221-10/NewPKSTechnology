package pl.kompikownia.pksmanager.timetable.base;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.kompikownia.pksmanager.cqrs.configuration.CqrsConfiguration;

@Configuration
@Import(CqrsConfiguration.class)
@EnableJpaRepositories(basePackages = "pl.kompikownia.pksmanager.timetable.infrastructure.repository.port")
@ComponentScan(basePackages = {
        "pl.kompikownia.pksmanager.timetable.infrastructure.repository.jpa",
        "pl.kompikownia.pksmanager.timetable.business.service.queryhandler",
        "pl.kompikownia.pksmanager.cqrs.domain"
})
@EntityScan(basePackages = "pl.kompikownia.pksmanager.timetable.infrastructure.entity")
@EnableAutoConfiguration
@EnableTransactionManagement
public class TestConfiguration {

}
