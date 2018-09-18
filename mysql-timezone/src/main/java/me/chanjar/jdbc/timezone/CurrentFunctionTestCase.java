package me.chanjar.jdbc.timezone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;
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

  private void doTest() {

    LOGGER.info("=========TEST CURRENT DATE/TIME FUNCTIONS===========");

    LOGGER.info("JVM Time Zone\t\t\t\t\t\t\t: {}", TimeZone.getDefault().getDisplayName());

    LOGGER.info("Test CURRENT_DATE()\t\t\t\t: {}", jdbcTemplate.queryForObject("select CURRENT_DATE()", java.sql.Date.class));
    LOGGER.info("Test CURRENT_TIME()\t\t\t\t: {}", jdbcTemplate.queryForObject("select CURRENT_TIME()", Time.class));
    LOGGER.info("Test CURRENT_TIMESTAMP()\t: {}", jdbcTemplate.queryForObject("select CURRENT_TIMESTAMP()", Date.class));

  }

}
