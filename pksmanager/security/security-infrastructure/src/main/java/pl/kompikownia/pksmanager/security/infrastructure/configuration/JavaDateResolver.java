package pl.kompikownia.pksmanager.security.infrastructure.configuration;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JavaDateResolver implements DateResolver {
    @Override
    public Date getActualDate() {
        return new Date();
    }
}
