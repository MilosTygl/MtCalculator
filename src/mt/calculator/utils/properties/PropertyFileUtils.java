package mt.calculator.utils.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import mt.calculator.config.PropertyConfig;
import mt.calculator.consts.Consts;

import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class PropertyFileUtils {

    private static final Logger LOGGER = Logger.getLogger(PropertyFileUtils.class);

    private static final String LOG_BEGIN = Consts.getLogBegin();
    private static final String LOG_END = Consts.getLogEnd();
    private static final String LOG_EXCEPTION = Consts.getLogException();

    private static PropertyFileUtils instance;
    private final Properties properties;

    /**
     *
     */
    private PropertyFileUtils() {

        LOGGER.debug("Constructor");

        properties = new Properties();

        try {
            FileInputStream fileInputStream;
            fileInputStream = new FileInputStream(PropertyConfig.getPropertyFilePath());
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     *
     * @return
     */
    public static PropertyFileUtils getInstance() {

        LOGGER.debug("getInstance");

        if (instance == null) {
            instance = new PropertyFileUtils();
            LOGGER.debug("New instance");
        } else {
            LOGGER.debug("Old instance");
        }

        return instance;
    }

    /**
     *
     */
    public static void reset() {

        LOGGER.debug("begin");

        instance = null;

        LOGGER.debug("end");
    }

    /**
     *
     * @param propertyName
     * @return
     */
    public String getProperty(String propertyName) {

        String propertyValue;
        propertyValue = properties.getProperty(propertyName);

        LOGGER.debug("propertyName: " + propertyName);
        LOGGER.debug("propertyValue: " + propertyValue);

        if (propertyValue == null) {
            LOGGER.error("Property not found");
        }

        return propertyValue;
    }

}
