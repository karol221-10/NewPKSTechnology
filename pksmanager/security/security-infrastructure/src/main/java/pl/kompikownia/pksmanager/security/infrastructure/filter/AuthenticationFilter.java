package pl.kompikownia.pksmanager.security.infrastructure.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.kompikownia.pksmanager.security.business.exception.BadTokenException;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.TokenFieldNames;
import pl.kompikownia.pksmanager.security.infrastructure.repository.jpa.UserCrudRepository;
import pl.kompikownia.pksmanager.security.infrastructure.repository.port.UserAuthenticationRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    public AuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        val authResult = this.attemptAuthentication(httpServletRequest, httpServletResponse);
        if(authResult != null) {
            this.successfulAuthentication(authResult);
        }
    }

    private String getToken(String header) {
        if(header == null)
            throw new BadTokenException(String.format("Header %s is empty", TokenFieldNames.HEADER_FIELD));
        if(!header.startsWith(TokenFieldNames.TOKEN_PREFIX))
            throw new BadTokenException(String.format("Header %s has bad format", header));
        return header.replace(TokenFieldNames.TOKEN_PREFIX, "");
    }

    private void validateToken(String token) {
        val date = tokenProvider.getExpirationDateFromToken(token);
        if(!date.after(new Date())) {
            throw new BadTokenException(String.format("Token %s is expired", token));
        }
    }

    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        val header = httpServletRequest.getHeader(TokenFieldNames.HEADER_FIELD);
        val token = getToken(header);
        validateToken(token);
        UserDetails userDetails = userAuthenticationRepository.findByUsername(tokenProvider.getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                tokenProvider.getAuthorities(token).stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }

    protected void successfulAuthentication(Authentication authResult) throws IOException, ServletException {
        val context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
    {
        val pathMacher = new AntPathMatcher();

        if(pathMacher.match("/swagger-ui.html", request.getServletPath())) {
            return true;
        }
        return false;
    }
}
