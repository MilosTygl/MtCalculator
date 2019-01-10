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
        String keyDebugMode;
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

}
