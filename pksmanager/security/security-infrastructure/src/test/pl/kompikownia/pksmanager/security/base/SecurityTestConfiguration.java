package pl.kompikownia.pksmanager.security.base;


import lombok.val;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.kompikownia.pksmanager.cqrs.configuration.CqrsConfiguration;
import pl.kompikownia.pksmanager.security.base.controller.TestController;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;
import pl.kompikownia.pksmanager.security.infrastructure.configuration.DateResolver;
import pl.kompikownia.pksmanager.security.infrastructure.configuration.WebSecurityConfig;
import pl.kompikownia.pksmanager.security.infrastructure.service.TokenProviderImpl;
import pl.kompikownia.pksmanager.security.test.Constants;

import static org.mockito.Mockito.when;

@Configuration
@Import({CqrsConfiguration.class, WebSecurityConfig.class})
@EnableJpaRepositories(basePackages = "pl.kompikownia.pksmanager.security.infrastructure.repository.port")
@ComponentScan(basePackages = {
        "pl.kompikownia.pksmanager.security.infrastructure.repository.jpa",
        "pl.kompikownia.pksmanager.security.infrastructure.repository",
        "pl.kompikownia.pksmanager.security.business.service.queryhandler",
        "pl.kompikownia.pksmanager.security.business.service.commandhandler",
        "pl.kompikownia.pksmanager.cqrs.domain",
        "pl.kompikownia.pksmanager.security.base.controller",
        "pl.kompikownia.pksmanager.security.base.configuration",
        "pl.kompikownia.pksmanager.security.base.queryhandler"
})
@EntityScan(basePackages = "pl.kompikownia.pksmanager.security.infrastructure.entity")
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SecurityTestConfiguration {

    @Bean
    @Primary
    DateResolver dateResolver() {
        val dateResolver = Mockito.mock(DateResolver.class);
        when(dateResolver.getActualDate()).thenReturn(Constants.ACTUAL_DATE_MOCK);
        return dateResolver;
    }
}