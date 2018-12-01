package mt.calculator.config;

/**
 * 
 * @author milos.tygl
 */
public class LogConfig {

    private static final String LOG_PROPERTY_FILE = "C:\\mt-dev\\_test\\MtCalculator\\log\\properties\\log.properties";

    /**
     * 
     */
    private LogConfig() {
    }

    /**
     * 
     * @return 
     */
    public static String getLogPropertyFile() {
        return LOG_PROPERTY_FILE;
    }

}
