package pl.kompikownia.pksmanager.security.infrastructure.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kompikownia.pksmanager.security.infrastructure.entity.SecurityUserEntity;

import java.util.Collection;

@AllArgsConstructor
public class UserDetailsModel implements UserDetails {

    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static UserDetailsModel of(SecurityUserEntity securityUserEntity) {
        return new UserDetailsModel(securityUserEntity.getUsername());
    }
}
