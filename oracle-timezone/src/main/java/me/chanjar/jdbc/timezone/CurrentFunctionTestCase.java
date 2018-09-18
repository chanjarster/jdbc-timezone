package me.chanjar.jdbc.timezone;

import org.apache.commons.lang3.StringUtils;
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

    int paddingLength = 25;

    LOGGER.info("=========TEST CURRENT DATE/TIME FUNCTIONS===========");
    LOGGER.info(StringUtils.rightPad("JVM Time Zone", paddingLength)
        + ": {}", TimeZone.getDefault().getDisplayName());

    LOGGER.info(StringUtils.rightPad("Test CURRENT_DATE", paddingLength)
        + ": {}", jdbcTemplate.queryForObject("select CURRENT_DATE from dual", String.class));

    LOGGER.info(StringUtils.rightPad("Test CURRENT_TIMESTAMP", paddingLength)
        + ": {}", jdbcTemplate.queryForObject("select CURRENT_TIMESTAMP from dual", String.class));

    LOGGER.info(StringUtils.rightPad("Test LOCALTIMESTAMP", paddingLength)
        + ": {}", jdbcTemplate.queryForObject("select LOCALTIMESTAMP from dual", String.class));

    LOGGER.info(StringUtils.rightPad("Test SYSDATE", paddingLength)
        + ": {}", jdbcTemplate.queryForObject("select SYSDATE from dual", String.class));

    LOGGER.info(StringUtils.rightPad("Test SYSTIMESTAMP", paddingLength)
        + ": {}", jdbcTemplate.queryForObject("select SYSTIMESTAMP from dual", String.class));

  }
}
