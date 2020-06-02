package pl.kompikownia.pksmanager.security.business.service;


import java.util.Date;
import java.util.List;

public interface TokenProvider {
    String generateToken(String username, List<String> permissions);
    String getUsernameFromToken(String token);
    Date getExpirationDateFromToken(String token);
    List<String> getAuthorities(String token);
}
