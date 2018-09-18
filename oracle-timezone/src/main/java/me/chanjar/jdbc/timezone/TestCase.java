package me.chanjar.jdbc.timezone;

import org.springframework.jdbc.core.JdbcTemplate;

public interface TestCase {

  boolean matches(String type);

  void doTest(JdbcTemplate jdbcTemplate) throws Exception;

}
