package mt.calculator.config;

/**
 *
 * @author milos.tygl
 */
public class PropertyConfig {

    private static final String PROPERTY_FILE = "conf/conf.properties";

    private PropertyConfig() {
    }

    /**
     *
     * @return
     */
    public static String getPropertyFilePath() {
        final String propertyFilePath;
        propertyFilePath = AppConfig.getAppHome() + PROPERTY_FILE;
        return propertyFilePath;
    }
}
