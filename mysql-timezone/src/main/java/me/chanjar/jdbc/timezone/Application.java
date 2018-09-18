package me.chanjar.jdbc.timezone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    if (!TestCaseUtils.hasTestCaseParam(args)) {
      System.err.println("请传入--test参数，比如--test=insert, --test=retrieve, --test=function");
      return;
    }
    SpringApplication.run(Application.class, args);
  }

}
