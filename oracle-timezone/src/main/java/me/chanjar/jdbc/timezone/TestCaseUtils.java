package me.chanjar.jdbc.timezone;

public abstract class TestCaseUtils {
  private TestCaseUtils() {
    // singleton
  }

  public static boolean hasTestCaseParam(String[] args) {

    return getTestCaseParam(args) != null;

  }

  public static boolean isTestCase(String[] args, String expectedTestCase) {
    if (!hasTestCaseParam(args)) {
      return false;
    }

    String testCaseParam = getTestCaseParam(args);
    String testCase = testCaseParam.replace("--test=", "");
    return testCase.equalsIgnoreCase(expectedTestCase);
  }

  private static String getTestCaseParam(String[] args) {
    if (args == null || args.length == 0) {
      return null;
    }

    for (String arg : args) {
      if (arg.startsWith("--test=")) {
        return arg;
      }
    }

    return null;
  }

}
