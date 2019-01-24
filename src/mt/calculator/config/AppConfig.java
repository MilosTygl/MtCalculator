package mt.calculator.config;

/**
 *
 * @author milos.tygl
 */
public class AppConfig {

    private static final String APP_HOME = "/mt-dev/_test/MtCalculator/";

    private AppConfig() {
    }

    /**
     *
     * @return
     */
    public static String getAppHome() {
        return APP_HOME;
    }
}
