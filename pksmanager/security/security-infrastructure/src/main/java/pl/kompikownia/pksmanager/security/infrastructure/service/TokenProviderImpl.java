package pl.kompikownia.pksmanager.security.infrastructure.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pl.kompikownia.pksmanager.security.business.projection.PermissionProjection;
import pl.kompikownia.pksmanager.security.business.projection.RoleProjection;
import pl.kompikownia.pksmanager.security.business.service.Authentication;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;
import pl.kompikownia.pksmanager.security.infrastructure.configuration.DateResolver;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.security.infrastructure.namemapper.TokenFieldNames.AUTHORITIES_KEY;

@Service
@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {

    @Value("${pl.kompikownia.pksmanager.security.signingkey}")
    private String signingKey;

    @Value("${pl.kompikownia.pksmanager.security.validity}")
    private long validitySeconds;

    private final DateResolver dateResolver;

    @Override
    public String generateToken(String username, List<String> userAuthorities) {
        String authorities = String.join(",", userAuthorities);
        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .setIssuedAt(dateResolver.getActualDate())
                .setExpiration(getExpirationDate())
                .compact();
    }

    private Date getExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateResolver.getActualDate());
        calendar.add(Calendar.SECOND, (int) validitySeconds);
        return calendar.getTime();
    }

    @Override
    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    @Override
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    @Override
    public List<String> getAuthorities(String token) {
        val claims = getAllClaimsFromToken(token);
        return Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .collect(Collectors.toList());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
