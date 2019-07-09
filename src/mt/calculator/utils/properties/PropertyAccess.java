package mt.calculator.utils.properties;

import mt.calculator.consts.Consts;

import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class PropertyAccess {

    private static final Logger LOGGER = Logger.getLogger(PropertyAccess.class);

    private static final String LOG_BEGIN = Consts.getLogBegin();
    private static final String LOG_END = Consts.getLogEnd();
    private static final String LOG_EXCEPTION = Consts.getLogException();

    private PropertyAccess() {
    }

    /**
     *
     * @return
     */
    public static boolean getPropertyDebugMode() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);

        return propertyDebugMode;
    }

    /**
     *
     * @return
     */
    public static boolean getPropertyDisplayColorGreen() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);

        return propertyDisplayColorGreen;
    }
}
