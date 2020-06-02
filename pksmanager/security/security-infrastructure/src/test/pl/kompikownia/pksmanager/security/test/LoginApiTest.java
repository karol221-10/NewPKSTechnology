package pl.kompikownia.pksmanager.security.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import pl.kompikownia.pksmanager.security.api.request.LoginRequest;
import pl.kompikownia.pksmanager.security.api.response.LoginResponse;
import pl.kompikownia.pksmanager.security.base.SecurityIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kompikownia.pksmanager.security.test.Constants.TOKEN_TO_TEST;

@Sql("/SecurityTest.sql")
public class LoginApiTest extends SecurityIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void shouldLoginUser() throws Exception{
        // given
        val request = LoginRequest.of(Constants.TEST_USER, Constants.TEST_PASSWORD);
        val requestJson = objectMapper.writeValueAsString(request);

        // when
        val result = mockMvc.perform(post("/api/login")
        .content(requestJson)
        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        val response = objectMapper.readValue(result.getResponse().getContentAsString(), LoginResponse.class);
        assertThat(response.getToken()).isEqualTo(TOKEN_TO_TEST);
    }

    @Test
    void shouldThrowExceptionWithBadCredentials() throws Exception{
        // given
        val request = LoginRequest.of(Constants.TEST_USER, Constants.BAD_PASSWORD);
        val requestJson = objectMapper.writeValueAsString(request);

        // when then
        assertThatThrownBy(() -> {
            val result = mockMvc.perform(post("/api/login")
                    .content(requestJson)
                    .contentType("application/json"))
                    .andReturn();
        }).isInstanceOf(NestedServletException.class);

    }
}
