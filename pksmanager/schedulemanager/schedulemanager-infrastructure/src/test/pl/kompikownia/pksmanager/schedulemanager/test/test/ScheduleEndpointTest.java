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
import pl.kompikownia.pksmanager.schedulemanager.api.dto.BusStopDto;
import pl.kompikownia.pksmanager.schedulemanager.api.dto.NewBusStopDto;
import pl.kompikownia.pksmanager.schedulemanager.api.request.AddNewBusStopRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.request.AddNewScheduleRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.request.UpdateBusStopRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.request.UpdateScheduleRequest;
import pl.kompikownia.pksmanager.schedulemanager.api.response.AddNewBusStopResponse;
import pl.kompikownia.pksmanager.schedulemanager.api.response.UpdateBusStopResponse;
import pl.kompikownia.pksmanager.schedulemanager.api.response.UpdateScheduleResponse;
import pl.kompikownia.pksmanager.schedulemanager.business.api.command.AddNewScheduleCommand;
import pl.kompikownia.pksmanager.schedulemanager.business.api.response.BusStop;
import pl.kompikownia.pksmanager.schedulemanager.test.base.IntegrationTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
        val request = AddNewScheduleRequest.builder()
                .busId(SCHEDULE_BUS_ID)
                .workerId(SCHEDULE_WORKER_ID)
                .busStops(List.of(
                        NewBusStopDto.builder()
                        .arrivalDate(FIRST_BUS_DATE)
                        .departureDate(FIRST_BUS_DATE.plusMinutes(5))
                        .townId(KIELCE_TOWN_ID)
                        .build(),
                        NewBusStopDto.builder()
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
    @Test
    public void shouldAddNewBusStopToSchedule() throws Exception {
        val request = AddNewBusStopRequest.builder()
                .arrivalDate(THIRD_BUS_DATE)
                .departureDate(THIRD_BUS_DATE.plusMinutes(10))
                .townId(WARSAW_TOWN_ID.toString())
                .build();

        val requestJson = objectMapper.writeValueAsString(request);

        val result = mockMvc.perform(post("/api/schedule/1/busstops")
                .content(requestJson)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        val resultObject = objectMapper.readValue(result.getResponse().getContentAsString(),
                AddNewBusStopResponse.class);
        assertThat(resultObject.getId()).isNotEmpty();
        assertThat(resultObject.getArrivalDate()).isEqualTo(THIRD_BUS_DATE);
        assertThat(resultObject.getDepartureDate()).isEqualTo(THIRD_BUS_DATE.plusMinutes(10));
        assertThat(resultObject.getTownId()).isEqualTo(WARSAW_TOWN_ID.toString());
        assertThat(resultObject.getScheduleId()).isEqualTo("1");
    }

    @Test
    public void shouldUpdateBusStop() throws Exception {
        val request = UpdateBusStopRequest.builder()
                .arrivalDate(SECOND_BUS_DATE)
                .departureDate(SECOND_BUS_DATE.plusMinutes(30))
                .townId(KIELCE_TOWN_ID.toString())
                .build();

        val requestJson = objectMapper.writeValueAsString(request);

        val result = mockMvc.perform(put("/api/schedule/1/busstops/1")
                .content(requestJson)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        val resultObject = objectMapper.readValue(result.getResponse().getContentAsString(),
                UpdateBusStopResponse.class);

        assertThat(resultObject.getId()).isEqualTo("1");
        assertThat(resultObject.getTownId()).isEqualTo(KIELCE_TOWN_ID);
        assertThat(resultObject.getArrivalDate()).isEqualTo(SECOND_BUS_DATE);
        assertThat(resultObject.getDepartureDate()).isEqualTo(SECOND_BUS_DATE.plusMinutes(30));
    }

    @Test
    public void shouldDeleteBusStop() throws Exception {
        mockMvc.perform(delete("/api/schedule/1/busstops/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteSchedule() throws Exception {
        mockMvc.perform(delete("/api/schedule/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateSchedule() throws Exception {
        val request = UpdateScheduleRequest.builder()
                .active(true)
                .workerId("3")
                .busId("4")
                .build();

        val requestJson = objectMapper.writeValueAsString(request);

        val result = mockMvc.perform(put("/api/schedule/1")
                .content(requestJson)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        val resultObject = objectMapper.readValue(result.getResponse().getContentAsString(),
                UpdateScheduleResponse.class);

        assertThat(resultObject.getId()).isEqualTo("1");
        assertThat(resultObject.getBusId()).isEqualTo("4");
        assertThat(resultObject.getWorkerId()).isEqualTo("3");
        assertThat(resultObject.getIsActive()).isEqualTo(true);
    }
}
