package pl.kompikownia.pksmanager.schedulemanager.test.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.AddNewScheduleCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.test.base.IntegrationTest;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static pl.kompikownia.pksmanager.schedulemanager.test.test.constants.ScheduleDataConstants.*;

@Sql("/ScheduleEndpointTest.sql")
public class ScheduleEndpointTest extends IntegrationTest {

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
    public void shouldCreateNewSchedule() throws Exception {
        val request = AddNewScheduleCommand.builder()
                .busId(SCHEDULE_BUS_ID)
                .workerId(SCHEDULE_WORKER_ID)
                .busStops(List.of(
                        BusStop.builder()
                        .arrivalDate(FIRST_BUS_DATE)
                        .departureDate(FIRST_BUS_DATE.plusMinutes(5))
                        .townId(KIELCE_TOWN_ID)
                        .build(),
                        BusStop.builder()
                        .arrivalDate(SECOND_BUS_DATE)
                        .departureDate(SECOND_BUS_DATE.plusMinutes(5))
                        .townId(BIALYSTOK_TOWN_ID)
                        .build())
                ).build();
        val requestJson = objectMapper.writeValueAsString(request);

        val result = mockMvc.perform(post("/api/schedule")
                .content(requestJson)
                .contentType("application/json"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
