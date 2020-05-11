package pl.kompikownia.pksmanager.security.business.service;

import pl.kompikownia.pksmanager.security.business.projection.RoleProjection;

import java.util.List;

public interface Authentication {
    List<RoleProjection> getRoles();
    String getLogin();
}
