package pl.kompikownia.pksmanager.busmanager.base;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BusManagerTestConfiguration.class)
public abstract class BusManagerIntegrationTest {
}
