package pl.kompikownia.pksmanager.busmanager.buisness.service.commandhandler;

import org.springframework.test.context.jdbc.Sql;
import pl.kompikownia.pksmanager.busmanager.base.BusManagerIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.Assertions.assertThat;


@Sql("/BusManagerTest.sql")
public class BusManagerEndpointsTest extends BusManagerIntegrationTest {

 /*   private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
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

        val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(), InspectionResponse.class);
        assertThat(mappedResult.getId()).isNotNull();
        assertThat(mappedResult.getType()).isNotNull();
        assertThat(mappedResult.getCreationDate()).isNotNull();
        assertThat(mappedResult.getExpiryDate()).isNotNull();
        assertThat(mappedResult.getComment()).isNotNull();
    }

    @Test
    public void shouldCreateNewInsurances() throws Exception{
        //given
        val request = PostInspectionCommand.builder()
                .id(1L)
                .busId(1L)
                .type("OC")
                .creationDate(LocalDateTime.parse("18-04-2020 09:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .expiryDate(LocalDateTime.parse("18-10-2020 09:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .comment("null")
                .build();
        val requestJson = objectMapper.writeValueAsString(request);

        //when
        val result = mockMvc.perform(post("/api/bus/1/insurances")
        .contentType("application/json")
        .content(requestJson))
                .andReturn();

        //then

        val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(), InsurancesResponse.class);
        assertThat(mappedResult.getId()).isNotNull();
        assertThat(mappedResult.getType()).isNotNull();
        assertThat(mappedResult.getCreationDate()).isNotNull();
        assertThat(mappedResult.getExpiryDate()).isNotNull();
        assertThat(mappedResult.getComment()).isNotNull();

    }

    @Test
    void shouldGetInspectionList() throws Exception{
        //given
        //when
        val result = mockMvc.perform(get("/api/bus/1/inspection")
        .contentType("application/json"))
                .andReturn();

        //then
        System.out.println(result.getResponse().getContentAsString());
      //  val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(), BusView[].class);
       // assertThat(mappedResult.length).isEqualTo(2);

    }

    @Test
    void shouldGetInsuranceList() throws Exception{
        //given
        //when
        val result = mockMvc.perform(get("/api/bus/1/insurance")
                .contentType("application/json"))
                .andReturn();

        //then
       *//* val mappedResult = objectMapper.readValue(result.getResponse().getContentAsString(), GetInsurancesListResponse.class);
        assertThat(mappedResult.getInsurancesList(1L).size()).isEqualTo(2);*//*

    }*/

}
