package pl.kompikownia.pksmanager.busmanager.buisness.service.commandhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.kompikownia.pksmanager.busmanager.base.BusManagerIntegrationTest;
import pl.kompikownia.pksmanager.busmanager.business.command.PostInspectionCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@Sql("/BusManagerTest.sql")
public class BusManagerEndpointsTest extends BusManagerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    public void shouldCreateNewInspection() throws Exception{
        //given
        val request = PostInspectionCommand.builder()
                .id(1L)
                .busId(1L)
                .type("CYCLIC")
                .creationDate(LocalDateTime.parse("18-04-2020 09:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .expiryDate(LocalDateTime.parse("18-05-2020 09:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .comment("Brak usterek")
                .build();
        val requestJson = objectMapper.writeValueAsString(request);

        //when
        val result = mockMvc.perform(post("/api/bus/1/inspection")
                .contentType("application/json")
                .content(requestJson))
                .andReturn();

        //then

       // val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(),InspectionResponse.class);
        //assertThat(mappedResult.)
    }

}
