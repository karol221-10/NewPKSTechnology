package pl.kompikownia.pksmanager.security.infrastructure.configuration;

import lombok.val;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;
import pl.kompikownia.pksmanager.security.business.internal.api.configuration.UrlWithoutAuthConfigurer;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;
import pl.kompikownia.pksmanager.security.infrastructure.filter.AuthenticationFilter;
import pl.kompikownia.pksmanager.security.infrastructure.repository.UserRepositoryImpl;
import pl.kompikownia.pksmanager.security.infrastructure.repository.port.UserAuthenticationRepository;
import pl.kompikownia.pksmanager.security.infrastructure.service.PermissionAspectChecker;
import pl.kompikownia.pksmanager.security.infrastructure.service.TokenProviderImpl;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private Collection<UrlWithoutAuthConfigurer> urlWithoutAuthConfigurers = new ArrayList<>();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        val urls = AnonymousUrlAccessFinder.scanForAnonymousAccessEndpoints();
        urls.addAll(getSwaggerUrls());
        return new AuthenticationFilter(getUrlsWithoutAuth());
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

    private List<String> getUrlsWithoutAuth() {
        return urlWithoutAuthConfigurers.stream()
                .map(UrlWithoutAuthConfigurer::getUrlWithoutAuth)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> getSwaggerUrls() {
        return List.of("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }
}
