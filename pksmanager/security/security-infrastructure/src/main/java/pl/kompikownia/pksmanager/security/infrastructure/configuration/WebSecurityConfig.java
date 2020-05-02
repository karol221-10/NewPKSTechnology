package pl.kompikownia.pksmanager.security.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;
import pl.kompikownia.pksmanager.security.infrastructure.filter.AuthenticationFilter;
import pl.kompikownia.pksmanager.security.infrastructure.repository.UserRepository;
import pl.kompikownia.pksmanager.security.infrastructure.repository.port.UserAuthenticationRepository;
import pl.kompikownia.pksmanager.security.infrastructure.service.TokenProviderImpl;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFilter authenticationFilter(final TokenProvider tokenProvider, final UserAuthenticationRepository userAuthenticationRepository) {
        return new AuthenticationFilter();
    }

    @Bean
    public TokenProvider tokenProvider() {
        return new TokenProviderImpl();
    }

    @Bean
    public UserAuthenticationRepository userAuthenticationRepository() {
        return new UserRepository();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .addFilterAt(authenticationFilter(tokenProvider(), userAuthenticationRepository()),
                        UsernamePasswordAuthenticationFilter.class);
    }
}
