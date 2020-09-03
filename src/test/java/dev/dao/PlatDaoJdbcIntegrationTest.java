package dev.dao;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.BddTestConfig;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { JdbcTestConfig.class })
@SpringJUnitConfig({ BddTestConfig.class, PlatDaoJdbc.class })
@ActiveProfiles({ "jdbc" })
public class PlatDaoJdbcIntegrationTest extends BasePlatDaoBddIntegrationTest {

}
