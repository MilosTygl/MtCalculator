package mt.calculator.utils.properties;

import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class PropertyAccess {

    private static final Logger LOGGER = Logger.getLogger(PropertyAccess.class);

    /**
     *
     * @return
     */
    public static boolean getPropertyDebugMode() {

        LOGGER.debug("begin");

        boolean propertyDebugMode;
        final String keyDebugMode;
        String valueDebugMode;
        final String valueDebugModeYes;
        final String valueDebugModeNo;

        propertyDebugMode = false;
        valueDebugModeYes = "yes";
        valueDebugModeNo = "no";
        keyDebugMode = "debugMode";

        PropertyFileUtils propertyFileUtils;
        propertyFileUtils = PropertyFileUtils.getInstance();

        valueDebugMode = propertyFileUtils.getProperty(keyDebugMode);
        if (valueDebugMode == null) {
            valueDebugMode = valueDebugModeNo;
        }
        if (valueDebugMode.equalsIgnoreCase(valueDebugModeYes)) {
            propertyDebugMode = true;
        }

        LOGGER.debug("end");

        return propertyDebugMode;
    }

    /**
     * 
     * @return 
     */
    public static boolean getPropertyDisplayColorGreen() {

        LOGGER.debug("begin");

        boolean propertyDisplayColorGreen;
        final String keyDisplayColorGreen;
        String valueDisplayColorGreen;
        final String valueDisplayColorGreenYes;
        final String valueDisplayColorGreenNo;

        propertyDisplayColorGreen = false;
        valueDisplayColorGreenYes = "yes";
        valueDisplayColorGreenNo = "no";
        keyDisplayColorGreen = "displayColorGreen";

        PropertyFileUtils propertyFileUtils;
        propertyFileUtils = PropertyFileUtils.getInstance();

        valueDisplayColorGreen = propertyFileUtils.getProperty(keyDisplayColorGreen);
        if (valueDisplayColorGreen == null) {
            valueDisplayColorGreen = valueDisplayColorGreenNo;
        }
        if (valueDisplayColorGreen.equalsIgnoreCase(valueDisplayColorGreenYes)) {
            propertyDisplayColorGreen = true;
        }

        LOGGER.debug("end");

        return propertyDisplayColorGreen;
    }
}
