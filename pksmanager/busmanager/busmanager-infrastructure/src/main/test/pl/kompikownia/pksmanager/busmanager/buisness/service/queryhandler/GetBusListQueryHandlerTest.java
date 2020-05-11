package pl.kompikownia.pksmanager.busmanager.buisness.service.queryhandler;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import pl.kompikownia.pksmanager.busmanager.base.BusManagerIntegrationTest;
import pl.kompikownia.pksmanager.busmanager.business.projection.BusProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.FuelProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.InspectionProjection;
import pl.kompikownia.pksmanager.busmanager.business.projection.InsurancesProjection;
import pl.kompikownia.pksmanager.busmanager.business.query.GetBusListQuery;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.busmanager.business.repository.FuelRepository;
import pl.kompikownia.pksmanager.busmanager.business.repository.InspectionRepository;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Sql("/GetBusListTest.sql")
public class GetBusListQueryHandlerTest extends BusManagerIntegrationTest {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private InsurancesRepository insurancesRepository;

    @Autowired
    private InspectionRepository inspectionRepository;

    @Autowired
    private QueryExecutor queryExecutor;

    @Test
    public void shouldReturnBus(){
        //given

        GetBusListQuery getBusListQuery = new GetBusListQuery();

        //when
        val result = queryExecutor.execute(getBusListQuery);
        System.out.println(result.get(0).toString());

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).hashCode())
                .isNotEqualTo(result.get(1)
                        .hashCode());

        assertThat(result)
                .containsAnyOf(
                        BusProjection.builder()
                                .id(1L)
                                .model("CITROEN")
                                .registrationNumber("TKA 2137")
                                .fuelProjections(List.of(FuelProjection.builder().id(1L)
                                        .busId(1L).quantify(10L).type("BENZINE").build()))
                                .inspectionProjections(List.of(InspectionProjection.builder()
                                        .id(1L)
                                        .busId(1L)
                                        .type("CYCLIC")
                                        .creationDate(LocalDateTime.parse("18-04-2020 09:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                                        .expiryDate(LocalDateTime.parse("18-05-2020 09:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                                        .comment("Brak usterek")
                                        .build()))
                                .insurancesProjections(List.of(InsurancesProjection.builder()
                                        .id(1L)
                                        .type("OC")
                                        .creationDate(LocalDateTime.parse("18-04-2020 09:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                                        .expiryDate(LocalDateTime.parse("18-10-2020 09:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                                        .busId(1L)
                                        .comment("null")
                                        .build()))
                                .build());
            }
}
