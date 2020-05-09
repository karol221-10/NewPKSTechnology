package pl.kompikownia.pksmanager.security.base.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.security.base.queryhandler.TestQueryWithInsufficientPermission;
import pl.kompikownia.pksmanager.security.base.queryhandler.TestQueryWithPermission;
import pl.kompikownia.pksmanager.security.base.queryhandler.TestQueryWithoutPermission;

@RestController
@AllArgsConstructor
public class TestController {

    private final QueryExecutor queryExecutor;

    @GetMapping("/api/withoutPermission")
    void testMethod() {
        queryExecutor.execute(new TestQueryWithoutPermission());
    }

    @GetMapping("/withoutAuth")
    Boolean testMethodWithoutAuth() {
        return true;
    }

    @GetMapping("/api/withInsufficientPermission")
    void testMethodWithInsufficientPermission() {
        queryExecutor.execute(new TestQueryWithInsufficientPermission());
    }

    @GetMapping("/api/withSufficientPermission")
    void testMethodWithSufficientPermission() {
        queryExecutor.execute(new TestQueryWithPermission());
    }
}
