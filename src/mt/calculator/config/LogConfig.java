package mt.calculator.config;

/**
 *
 * @author milos.tygl
 */
public class LogConfig {

    private static final String LOG_PROPERTY_FILE = "log/properties/log.properties";

    private LogConfig() {
    }

    /**
     *
     * @return
     */
    public static String getLogPropertyFile() {
        String logPropertyFilePath;
        logPropertyFilePath = AppConfig.getAppHome() + LOG_PROPERTY_FILE;
        return logPropertyFilePath;
    }
}
