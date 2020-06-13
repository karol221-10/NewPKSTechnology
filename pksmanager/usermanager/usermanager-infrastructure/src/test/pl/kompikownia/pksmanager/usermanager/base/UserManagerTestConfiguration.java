package pl.kompikownia.pksmanager.usermanager.base;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.kompikownia.pksmanager.cqrs.configuration.CqrsConfiguration;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;

@Configuration
@Import({CqrsConfiguration.class})
@EnableJpaRepositories(basePackages = "pl.kompikownia.pksmanager.busmanager.infrastructure")
@ComponentScan(basePackages = {
        "pl.kompikownia.pksmanager.busmanager.infrastructure.repository",
        "pl.kompikownia.pksmanager.usermanager.business.service.command",
        "pl.kompikownia.pksmanager.usermanager.business.service.query",
        "pl.kompikownia.pksmanager.cqrs.domain",
        "pl.kompikownia.pksmanager.usermanager.api.endpoint",
        "pl.kompikownia.pksmanager.security.business.service.queryhandler",
        "pl.kompikownia.pksmanager.security.business.service.commandhandler",
        "pl.kompikownia.pksmanager.security.infrastructure.repository.jpa",
        "pl.kompikownia.pksmanager.security.infrastructure.repository",
})
@EntityScan(basePackages = {
        "pl.kompikownia.pksmanager.usermanager.infrastructure.entity",
        "pl.kompikownia.pksmanager.security.infrastructure.entity"
})
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class UserManagerTestConfiguration {

    @MockBean
    private TokenProvider tokenProvider;
}