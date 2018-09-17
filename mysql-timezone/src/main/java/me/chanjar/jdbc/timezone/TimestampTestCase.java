package me.chanjar.jdbc.timezone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class TimestampTestCase implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(TimestampTestCase.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {

    LOGGER.info("=========TEST TIMESTAMP TYPE===========");

    doInsert("Asia/Shanghai");
    doRetrieve("Asia/Shanghai");
    doRetrieve("Europe/Paris");

    LOGGER.info("");
  }

  private void doInsert(String timezone) throws ParseException {

    TimeZone.setDefault(TimeZone.getTimeZone(timezone));

    LOGGER.info("Insert data, Time Zone\t\t\t\t: {}", TimeZone.getDefault().getDisplayName());

    String localDateString = "2018-09-14 10:00:00";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date localDate = sdf.parse(localDateString);

    LOGGER.info("java.util.Date\t\t\t\t\t\t\t\t: {}", localDateString);

    jdbcTemplate.execute("delete from test");

    LOGGER.info("Insert into timestamp column\t: {}", localDateString);
    jdbcTemplate.update("insert into test values(?)", localDate);

  }

  private void doRetrieve(String timezone) {

    TimeZone.setDefault(TimeZone.getTimeZone(timezone));

    LOGGER.info("--------------------");
    LOGGER.info("Retrieve data, Time Zone\t\t\t: {}", TimeZone.getDefault().getDisplayName());

    LOGGER.info("Retrieve java.util.Date\t\t\t\t: {}",
        jdbcTemplate.queryForObject("select created_at from test", Date.class));

    LOGGER.info("Retrieve formatted string\t\t\t: {}",
        jdbcTemplate.queryForObject("SELECT DATE_FORMAT(created_at, '%Y-%m-%d %H:%i:%s') from test", String.class));
  }

}
