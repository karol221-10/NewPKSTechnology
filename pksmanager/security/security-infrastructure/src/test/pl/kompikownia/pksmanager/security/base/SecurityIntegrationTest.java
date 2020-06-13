package pl.kompikownia.pksmanager.security.base;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kompikownia.pksmanager.security.base.controller.TestController;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SecurityTestConfiguration.class)
@ActiveProfiles("SECURITY")
public abstract class SecurityIntegrationTest {

}
