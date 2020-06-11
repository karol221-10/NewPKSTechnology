package pl.kompikownia.pksmanager.security.infrastructure.configuration;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;
import pl.kompikownia.pksmanager.security.infrastructure.filter.AuthenticationFilter;
import pl.kompikownia.pksmanager.security.infrastructure.repository.UserSecurityRepositoryImpl;
import pl.kompikownia.pksmanager.security.infrastructure.repository.port.UserAuthenticationRepository;
import pl.kompikownia.pksmanager.security.infrastructure.service.integration.facebook.FacebookConnectionSignup;
import pl.kompikownia.pksmanager.security.infrastructure.service.PermissionAspectChecker;
import pl.kompikownia.pksmanager.security.infrastructure.service.TokenProviderImpl;
import pl.kompikownia.pksmanager.security.infrastructure.service.integration.facebook.FacebookSignInAdapter;

import java.util.*;

@Configuration
@EnableWebSecurity
@Profile("SECURITY")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${pl.kompikownia.pksmanager.facebook.appId}")
    private String appId;

    @Value("${pl.kompikownia.pksmanager.facebook.secretId}")
    private String appSecret;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;

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
        return new UserSecurityRepositoryImpl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .headers().frameOptions().disable().and()
                .addFilterAt(authenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    private List<String> getSwaggerUrls() {
        return List.of("/", "/h2-console/**", "/signin/**","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }

    @Bean
    public ProviderSignInController providerSignInController(final UserRepository userSecurityRepositoryImpl,
                                                             final TokenProvider tokenProvider,
                                                             @Value("${pl.kompikownia.pksmanager.facebook.redirectUrl}")
                                                             final String redirectUrl) {
        ConnectionFactoryLocator connectionFactoryLocator =
                connectionFactoryLocator();
        UsersConnectionRepository usersConnectionRepository =
                getUsersConnectionRepository(connectionFactoryLocator);
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
                .setConnectionSignUp(facebookConnectionSignup);
        return new ProviderSignInController(connectionFactoryLocator,
                usersConnectionRepository, new FacebookSignInAdapter(userSecurityRepositoryImpl, redirectUrl, tokenProvider));
    }

    private ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
        return registry;
    }

    private UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator
                                                                           connectionFactoryLocator) {
        return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
    }
}
