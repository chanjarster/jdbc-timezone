package me.chanjar.jdbc.timezone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class InsertTestCase implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(InsertTestCase.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {

    if (TestCaseUtils.isTestCase(args, "insert")) {
      doTest();
    }

  }

  public void doTest() throws Exception {

    jdbcTemplate.execute("delete from test");

    LOGGER.info("=========TEST INSERT DATE/TIME TYPTES===========");
    LOGGER.info("JVM Time Zone\t\t\t\t\t\t\t\t: {}", TimeZone.getDefault().getDisplayName());

    String localDateString = "2018-09-14 10:00:00";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date localDate = sdf.parse(localDateString);

    LOGGER.info("Insert data java.util.Date\t: {}", localDateString);

    LOGGER.info("Insert into DATE, TIMESTAMP, TIMESTAMP WITH TIME ZONE, TIMESTAMP WITH LOCAL TIME ZONE column\t: {}",
        localDateString);
    jdbcTemplate
        .update("insert into test(date_field, ts_field, ts_tz_field, ts_ltz_field) values(?, ?, ?, ?)",
            localDate, localDate, localDate, localDate);

  }

}
