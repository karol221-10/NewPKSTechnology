package pl.kompikownia.pksmanager.usermanager.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.kompikownia.pksmanager.usermanager.api.request.CreateNewUserRequest;
import pl.kompikownia.pksmanager.usermanager.api.response.CreateNewUserResponse;
import pl.kompikownia.pksmanager.usermanager.base.UserManagerIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestPropertySource("classpath:application.properties")
@Sql("/UserManagerTest.sql")
public class UserEndpointTest extends UserManagerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        // given
        val request = CreateNewUserRequest.builder()
                .name(TestConstants.USER_NAME)
                .surname(TestConstants.USER_SURNAME)
                .email(TestConstants.USER_EMAIL)
                .login(TestConstants.USER_LOGIN)
                .password(TestConstants.USER_PASSWORD)
                .build();
        val requestJson = objectMapper.writeValueAsString(request);

        // when
        val result = mockMvc.perform(post("/api/user")
        .contentType("application/json")
        .content(requestJson))
                .andReturn();

        //then
        val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(), CreateNewUserResponse.class);
        assertThat(mappedResult.getId()).isNotNull();
        assertThat(mappedResult.getEmail()).isEqualTo(TestConstants.USER_EMAIL);
        assertThat(mappedResult.getName()).isEqualTo(TestConstants.USER_NAME);
        assertThat(mappedResult.getSurname()).isEqualTo(TestConstants.USER_SURNAME);
        assertThat(mappedResult.getSecurityUserId()).isNotEmpty();
    }
}
