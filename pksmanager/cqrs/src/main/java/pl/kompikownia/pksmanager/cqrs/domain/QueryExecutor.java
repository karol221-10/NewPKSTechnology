package pl.kompikownia.pksmanager.cqrs.domain;

import pl.kompikownia.pksmanager.cqrs.domain.Query;
import pl.kompikownia.pksmanager.cqrs.domain.QueryHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public interface QueryExecutor {
    <T, Q extends Query<T>> T execute(Q q);
}
