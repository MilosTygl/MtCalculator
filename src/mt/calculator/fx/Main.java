package mt.calculator.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mt.calculator.config.LogConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author milos.tygl
 */
public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    /**
     * 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {

        LOGGER.debug("begin");

        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene;
        scene = new Scene(root);

        stage.setTitle("MT Calculator JavaFX [beta]");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        LOGGER.debug("end");
    }

    /**
     * 
     */
    @Override
    public void stop() {

        LOGGER.debug("begin");

        LOGGER.debug("end");
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        PropertyConfigurator.configure(LogConfig.getLogPropertyFile());

        LOGGER.debug("BEGIN");

        launch(args);

        LOGGER.debug("END");
    }

}
