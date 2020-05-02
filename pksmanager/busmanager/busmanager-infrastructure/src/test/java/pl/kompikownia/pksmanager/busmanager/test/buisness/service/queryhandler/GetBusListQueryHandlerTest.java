package pl.kompikownia.pksmanager.busmanager.test.buisness.service.queryhandler;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import pl.kompikownia.pksmanager.busmanager.base.BusManagerIntegrationTest;
import pl.kompikownia.pksmanager.busmanager.business.query.GetBusListQuery;
import pl.kompikownia.pksmanager.busmanager.business.repository.BusRepository;
import pl.kompikownia.pksmanager.busmanager.business.repository.FuelRepository;
import pl.kompikownia.pksmanager.busmanager.business.repository.InspectionRepository;
import pl.kompikownia.pksmanager.busmanager.business.repository.InsurancesRepository;
import pl.kompikownia.pksmanager.cqrs.domain.QueryExecutor;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Sql("/GetBusListTest.sql")
public class GetBusListQueryHandlerTest extends BusManagerIntegrationTest{

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

        //then
        assertThat(result).isNotNull();




    }
}
