package pl.kompikownia.pksmanager.security.base.configuration;

import org.springframework.stereotype.Component;
import pl.kompikownia.pksmanager.security.business.internal.api.configuration.UrlWithoutAuthConfigurer;

import java.util.List;

@Component
public class TestUrlsWithoutAuth implements UrlWithoutAuthConfigurer {
    @Override
    public List<String> getUrlWithoutAuth() {
        return List.of("/withoutAuth");
    }
}
