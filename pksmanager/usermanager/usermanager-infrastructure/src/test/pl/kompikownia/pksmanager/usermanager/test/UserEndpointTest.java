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
import pl.kompikownia.pksmanager.usermanager.api.request.CreateNewWorkerRequest;
import pl.kompikownia.pksmanager.usermanager.api.response.CreateNewUserResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.CreateNewWorkerResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.GetUserListResponse;
import pl.kompikownia.pksmanager.usermanager.api.response.GetWorkerListResponse;
import pl.kompikownia.pksmanager.usermanager.base.UserManagerIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kompikownia.pksmanager.usermanager.test.TestConstants.getResponseWorkerData;

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
        val result = mockMvc.perform(post("/api/users")
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

    @Test
    public void shouldCreateNewWorker() throws Exception {
        // given
        val request = CreateNewWorkerRequest.builder()
                .name(TestConstants.USER_NAME)
                .surname(TestConstants.USER_SURNAME)
                .driverLicenseNumber(TestConstants.USER_DRIVER_LICENSE_NUMBER)
                .email(TestConstants.USER_EMAIL)
                .pesel(TestConstants.USER_PESEL)
                .personIdNumber(TestConstants.USER_PERSONAL_ID_NUMBER)
                .login(TestConstants.USER_LOGIN)
                .password(TestConstants.USER_PASSWORD)
                .build();
        val requestJson = objectMapper.writeValueAsString(request);
        // when
        val result = mockMvc.perform(post("/api/workers")
                .contentType("application/json")
                .content(requestJson))
                .andReturn();
        // then
        val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(), CreateNewWorkerResponse.class);
        assertThat(mappedResult.getId()).isNotEmpty();
        assertThat(mappedResult.getSecurityUserId()).isNotEmpty();
        assertThat(mappedResult.getName()).isEqualTo(TestConstants.USER_NAME);
        assertThat(mappedResult.getSurname()).isEqualTo(TestConstants.USER_SURNAME);
        assertThat(mappedResult.getEmail()).isEqualTo(TestConstants.USER_EMAIL);
        assertThat(mappedResult.getDriverLicenseNumber()).isEqualTo(TestConstants.USER_DRIVER_LICENSE_NUMBER);
        assertThat(mappedResult.getPesel()).isEqualTo(TestConstants.USER_PESEL);
        assertThat(mappedResult.getPersonIdNumber()).isEqualTo(TestConstants.USER_PERSONAL_ID_NUMBER);
    }

    @Test
    void shouldGetUsersList() throws Exception {
        // given
        // when
        val result = mockMvc.perform(get("/api/users")
            .contentType("application/json"))
                .andReturn();

        // then
        val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(), GetUserListResponse.class);
        assertThat(mappedResult.getUsers()).containsAll(TestConstants.getResponseUserDataList());
    }

    @Test
    void shouldGetWorkerList() throws Exception {
        // given
        // when
        val result = mockMvc.perform(get("/api/workers")
            .contentType("application/json"))
                .andReturn();
        // then
        val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(), GetWorkerListResponse.class);
        assertThat(mappedResult.getWorkers().size()).isEqualTo(1);
        assertThat(mappedResult.getWorkers()).contains(getResponseWorkerData());
    }

    @Test
    void shouldDeactivateUser() throws Exception {
        // given
        // when
        val result = mockMvc.perform(delete("/api/users/1")
            .contentType("application/json"))
                .andExpect(status().isOk());

        val userResult = mockMvc.perform(get("/api/users")
                .contentType("application/json"))
                .andReturn();

        // then
        val mappedResult = objectMapper.readValue(userResult.getResponse().getContentAsString(), GetUserListResponse.class);

        assertThat(mappedResult.getUsers().size()).isEqualTo(1);
    }
}
