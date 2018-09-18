package me.chanjar.jdbc.timezone;

import org.apache.commons.lang3.StringUtils;
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

  private void doTest() throws ParseException {

    int paddingLength = 30;
    jdbcTemplate.execute("delete from test");

    LOGGER.info("=========TEST INSERT DATE/TIME TYPTES===========");
    LOGGER.info(StringUtils.rightPad("JVM Time Zone", paddingLength)
        + ": {}", TimeZone.getDefault().getDisplayName());

    String localDateString = "2018-09-14 10:00:00";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date localDate = sdf.parse(localDateString);

    LOGGER.info(StringUtils.rightPad("java.util.Date", paddingLength)
        + ": {}", localDateString);

    LOGGER.info(StringUtils.rightPad("Insert into timestamp column", paddingLength)
        + ": {}", localDateString);
    jdbcTemplate.update("insert into test values(?)", localDate);

  }

}
