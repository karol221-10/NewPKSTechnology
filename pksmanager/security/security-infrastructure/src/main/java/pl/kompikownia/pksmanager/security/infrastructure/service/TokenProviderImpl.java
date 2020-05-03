package pl.kompikownia.pksmanager.security.infrastructure.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pl.kompikownia.pksmanager.security.business.projection.PermissionProjection;
import pl.kompikownia.pksmanager.security.business.projection.RoleProjection;
import pl.kompikownia.pksmanager.security.business.service.Authentication;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.security.infrastructure.namemapper.TokenFieldNames.AUTHORITIES_KEY;

@Service
public class TokenProviderImpl implements TokenProvider {

    @Value("{pl.kompikownia.pksmanager.security.signingkey}")
    private String signingKey;

    @Value("${pl.kompikownia.pksmanager.security.validity}")
    private long validitySeconds;

    @Override
    public String generateToken(String username, List<String> userAuthorities) {
        String authorities = String.join(",", userAuthorities);
        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .setIssuedAt(Date.from(
                        LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setExpiration(Date.from(
                        LocalDateTime.now()
                                .plusSeconds(validitySeconds)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
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
