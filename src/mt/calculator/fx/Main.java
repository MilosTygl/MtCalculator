package mt.calculator.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import mt.calculator.config.LogConfig;
import mt.calculator.consts.Consts;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author milos.tygl
 */
public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    private static final String LOG_BEGIN = Consts.getLogBegin();
    private static final String LOG_END = Consts.getLogEnd();

    private static final String APP_TITLE = Consts.getAppTitle();

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        LOGGER.debug(LOG_BEGIN);

        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene;
        scene = new Scene(root);

        stage.setTitle(APP_TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    @Override
    public void stop() {

        LOGGER.debug(LOG_BEGIN);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        PropertyConfigurator.configure(LogConfig.getLogPropertyFile());

        LOGGER.debug(LOG_BEGIN);

        launch(args);

        LOGGER.debug(LOG_END);
    }

}
