package me.chanjar.jdbc.timezone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.TimeZone;

@Component
public class RetrieveTestCase implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(RetrieveTestCase.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {

    if (TestCaseUtils.isTestCase(args, "retrieve")) {
      doTest();
    }

  }

  public void doTest() {

    int paddingLength = 68;

    LOGGER.info("=========TEST RETRIEVE DATE/TIME TYPTES===========");

    LOGGER.info(StringUtils.rightPad("JVM Time Zone", paddingLength)
        + ": {}", TimeZone.getDefault().getDisplayName());

    LOGGER.info(StringUtils.rightPad("Retrieve java.util.Date from DATE column", paddingLength)
            + ": {}",
        jdbcTemplate.queryForObject("select date_field from test", Date.class));

    LOGGER.info(StringUtils.rightPad("Retrieve java.util.Date from TIMESTAMP column", paddingLength)
            + ": {}",
        jdbcTemplate.queryForObject("select ts_field from test", Date.class));

    LOGGER.info(StringUtils.rightPad("Retrieve java.util.Date from TIMESTAMP WITH TIME ZONE column", paddingLength)
            + ": {}",
        jdbcTemplate.queryForObject("select ts_tz_field from test", Date.class));

    LOGGER.info(StringUtils.rightPad("Retrieve java.util.Date from TIMESTAMP WITH LOCAL TIME ZONE column", paddingLength)
            + ": {}",
        jdbcTemplate.queryForObject("select ts_ltz_field from test", Date.class));

    LOGGER.info(StringUtils.rightPad("Retrieve formatted string from DATE column", paddingLength)
            + ": {}",
        jdbcTemplate.queryForObject("SELECT TO_CHAR(date_field, 'YYYY-MM-DD HH24:MI:SS') from test", String.class));

    LOGGER.info(StringUtils.rightPad("Retrieve formatted string from TIMESTAMP column", paddingLength)
            + ": {}",
        jdbcTemplate.queryForObject("SELECT TO_CHAR(ts_field, 'YYYY-MM-DD HH24:MI:SS') from test", String.class));

    LOGGER.info(StringUtils.rightPad("Retrieve formatted string from TIMESTAMP WITH TIME ZONE column", paddingLength)
            + ": {}",
        jdbcTemplate.queryForObject("SELECT TO_CHAR(ts_tz_field, 'YYYY-MM-DD HH24:MI:SS TZH:TZM TZR TZD') from test",
            String.class));

    LOGGER.info(StringUtils.rightPad("Retrieve formatted string from TIMESTAMP WITH LOCAL TIME ZONE column", paddingLength)
            + ": {}",
        jdbcTemplate.queryForObject("SELECT TO_CHAR(ts_ltz_field, 'YYYY-MM-DD HH24:MI:SS') from test", String.class));

  }

}
