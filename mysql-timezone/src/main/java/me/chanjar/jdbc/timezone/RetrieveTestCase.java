package me.chanjar.jdbc.timezone;

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

  private void doTest() {

    LOGGER.info("=========TEST RETRIEVE DATE/TIME TYPTES===========");

    LOGGER.info("JVM Time Zone\t\t\t\t\t\t\t\t: {}", TimeZone.getDefault().getDisplayName());

    LOGGER.info("Retrieve java.util.Date\t\t\t: {}",
        jdbcTemplate.queryForObject("select created_at from test", Date.class));

    LOGGER.info("Retrieve formatted string\t\t: {}",
        jdbcTemplate.queryForObject("SELECT DATE_FORMAT(created_at, '%Y-%m-%d %H:%i:%s') from test", String.class));
  }

}
