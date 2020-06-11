package pl.kompikownia.pksmanager.security.infrastructure.service.integration.facebook;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import pl.kompikownia.pksmanager.security.business.repository.UserRepository;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FacebookSignInAdapter implements SignInAdapter {

    private final UserRepository userRepository;

    private final String redirectUrl;

    private final TokenProvider tokenProvider;

    @Override
    public String signIn(String s, Connection<?> connection, NativeWebRequest nativeWebRequest) {
       val user = userRepository.getUserWithPermissionsById(Long.parseLong(s));
       SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
               user.getUsername(), null,
               user.getPermissionNames().stream()
               .map(SimpleGrantedAuthority::new)
               .collect(Collectors.toList())
       ));
       val token = tokenProvider.generateToken(user.getUsername(), user.getPermissionNames());
       val request = (ServletWebRequest) nativeWebRequest;
       request.getResponse().addCookie(getSocialAuthenticationCookie(token));
       return redirectUrl;
    }

    private Cookie getSocialAuthenticationCookie(String token) {
        Cookie socialAuthCookie = new Cookie("CONTEXT_DATA", token);
        socialAuthCookie.setPath("/");
        socialAuthCookie.setMaxAge(10);
        return socialAuthCookie;
    }
}
