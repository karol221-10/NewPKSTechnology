package pl.kompikownia.pksmanager.security.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureException;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import pl.kompikownia.pksmanager.security.base.SecurityIntegrationTest;
import pl.kompikownia.pksmanager.security.base.controller.TestController;
import pl.kompikownia.pksmanager.security.business.exception.BadTokenException;
import pl.kompikownia.pksmanager.security.business.exception.InsufficientPermissionException;
import pl.kompikownia.pksmanager.security.infrastructure.configuration.DateResolver;
import pl.kompikownia.pksmanager.security.infrastructure.namemapper.TokenFieldNames;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.kompikownia.pksmanager.security.test.Constants.*;

@TestPropertySource("/application.properties")
@Sql("/SecurityTest.sql")
public class MvcAuthorizationTest extends SecurityIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldThrowBadTokenException()  {
        assertThatThrownBy(() -> {
            mockMvc.perform(get("/api/withoutPermission"))
                    .andReturn();
        }).isInstanceOf(BadTokenException.class);
    }

    @Test
    public void shouldReturnWithoutAuth() throws Exception {
        mockMvc.perform(get("/withoutAuth"))
                .andReturn();
    }

    @Test
    public void shouldAuthorizeUserWithoutPermission() throws Exception{
        // when then
        mockMvc.perform(get("/api/withoutPermission")
                .header(TokenFieldNames.HEADER_FIELD,BEARER_TOKEN_TO_TEST))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotAuthorizeWithExpiredToken() {
        // when then
        assertThatThrownBy(() -> {
            mockMvc.perform(get("/api/withoutPermission")
                    .header(TokenFieldNames.HEADER_FIELD, BEARER_EXPIRED_TOKEN))
                    .andReturn();
        }).isInstanceOf(BadTokenException.class);
    }

    @Test
    public void shouldNotAuthorizeByMisplacedSignatureToken() {
        // when then
        assertThatThrownBy(() -> {
            mockMvc.perform(get("/api/withoutPermission")
                    .header(TokenFieldNames.HEADER_FIELD, BEARER_BAD_SIGNATURE_TOKEN))
                    .andReturn();
        }).isInstanceOf(SignatureException.class);
    }

    @Test
    public void shouldThrowExceptionWhenTokenWithTokenWithoutPermission() throws Exception{
        assertThatThrownBy(() -> {
            mockMvc.perform(get("/api/withInsufficientPermission")
                    .header(TokenFieldNames.HEADER_FIELD, BEARER_TOKEN_TO_TEST))
                    .andExpect(status().isOk());
        }).isInstanceOf(NestedServletException.class);
    }

    @Test
    public void shouldAuthorizeWithTestPermission() throws Exception {
        mockMvc.perform(get("/api/withSufficientPermission")
                .header(TokenFieldNames.HEADER_FIELD, BEARER_TOKEN_TO_TEST))
                .andExpect(status().isOk());
    }
}
