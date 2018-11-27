package mt.calculator.config;

public class LogConfig {

    private static final String LOG_PROPERTY_FILE = "C:\\mt-dev\\_test\\MtCalculator\\log\\properties\\log.properties";

    private LogConfig() {
    }

    public static String getLogPropertyFile() {
        return LOG_PROPERTY_FILE;
    }

}
