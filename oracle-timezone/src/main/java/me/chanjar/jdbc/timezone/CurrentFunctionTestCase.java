package me.chanjar.jdbc.timezone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class CurrentFunctionTestCase implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(CurrentFunctionTestCase.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {

    if (TestCaseUtils.isTestCase(args, "function")) {
      doTest();
    }

  }

  public void doTest() {

    LOGGER.info("=========TEST CURRENT DATE/TIME FUNCTIONS===========");
    LOGGER.info("JVM Time Zone\t\t\t\t\t\t\t\t: {}", TimeZone.getDefault().getDisplayName());

    LOGGER.info("Test CURRENT_DATE\t\t\t\t\t\t: {}", jdbcTemplate.queryForObject("select CURRENT_DATE from dual", String.class));
    LOGGER.info("Test CURRENT_TIMESTAMP\t\t\t: {}", jdbcTemplate.queryForObject("select CURRENT_TIMESTAMP from dual", String.class));
    LOGGER.info("Test LOCALTIMESTAMP\t\t\t\t\t: {}", jdbcTemplate.queryForObject("select LOCALTIMESTAMP from dual", String.class));
    LOGGER.info("Test SYSDATE\t\t\t\t\t\t\t\t: {}", jdbcTemplate.queryForObject("select SYSDATE from dual", String.class));
    LOGGER.info("Test SYSTIMESTAMP\t\t\t\t\t\t: {}", jdbcTemplate.queryForObject("select SYSTIMESTAMP from dual", String.class));

  }
}
