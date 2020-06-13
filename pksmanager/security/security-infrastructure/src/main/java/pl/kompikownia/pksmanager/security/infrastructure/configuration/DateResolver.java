package pl.kompikownia.pksmanager.security.infrastructure.configuration;

import java.time.LocalDateTime;
import java.util.Date;

public interface DateResolver {
    Date getActualDate();
}
