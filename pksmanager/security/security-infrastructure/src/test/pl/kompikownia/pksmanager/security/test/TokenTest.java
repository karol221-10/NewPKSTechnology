package pl.kompikownia.pksmanager.security.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;
import pl.kompikownia.pksmanager.security.base.SecurityIntegrationTest;
import pl.kompikownia.pksmanager.security.business.query.GetTokenForUserQuery;
import pl.kompikownia.pksmanager.security.business.service.TokenProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.kompikownia.pksmanager.security.test.Constants.*;

@TestPropertySource("classpath:application.properties")
@Sql("/SecurityTest.sql")
public class TokenTest extends SecurityIntegrationTest {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private TokenProvider tokenProvider;

    @Test
    public void shouldGenerateToken() {

        // when
        val token = queryExecutor.execute(GetTokenForUserQuery.builder()
                .username(TEST_USER)
                .password(TEST_PASSWORD)
                .build());
        // then
        assertThat(token).isNotEmpty();
    }

    @Test
    public void shouldReturnExpirationDateFromToken() {
        // when
        val expirationDate = tokenProvider.getExpirationDateFromToken(TOKEN_TO_TEST);

        // then
        val formattedExpirationDate = DATE_FORMAT.format(expirationDate);
        assertThat(formattedExpirationDate).isEqualTo(TOKEN_EXPIRATION_DATE_MOCK_STRING);
    }

    @Test
    public void shouldReturnUsernameFromToken() {
        // when
        val username = tokenProvider.getUserIdFromToken(TOKEN_TO_TEST);

        // then
        assertThat(username).isEqualTo(TEST_USER);
    }

    @Test
    public void shouldReturnPermissionsFromToken() {
        // when
        val permissions = tokenProvider.getAuthorities(TOKEN_TO_TEST);

        // then
        assertThat(permissions).isEqualTo(PERMISSION_LIST);
    }
}
