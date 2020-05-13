package pl.kompikownia.pksmanager.security.infrastructure.configuration;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;
import pl.kompikownia.pksmanager.security.infrastructure.filter.AuthenticationFilter;
import pl.kompikownia.pksmanager.security.infrastructure.repository.UserRepositoryImpl;
import pl.kompikownia.pksmanager.security.infrastructure.repository.port.UserAuthenticationRepository;
import pl.kompikownia.pksmanager.security.infrastructure.service.PermissionAspectChecker;
import pl.kompikownia.pksmanager.security.infrastructure.service.TokenProviderImpl;

import java.util.*;

@Configuration
@EnableWebSecurity
@Profile("SECURITY")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        val urls = AnonymousUrlAccessFinder.scanForAnonymousAccessEndpoints();
        urls.addAll(getSwaggerUrls());
        return new AuthenticationFilter(urls);
    }

    @Bean
    public PermissionAspectChecker permissionAspectChecker() {
        return new PermissionAspectChecker();
    }

    @Bean
    public TokenProvider tokenProvider(DateResolver dateResolver) {
        return new TokenProviderImpl(dateResolver);
    }

    @Bean
    public UserAuthenticationRepository userAuthenticationRepository() {
        return new UserRepositoryImpl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .addFilterAt(authenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    private List<String> getSwaggerUrls() {
        return List.of("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }
}
