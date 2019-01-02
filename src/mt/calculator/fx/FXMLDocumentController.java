package mt.calculator.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import mt.calculator.hw.MtCalculator;
import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class FXMLDocumentController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(FXMLDocumentController.class);

    @FXML
    private Pane normalDisplayPane;

    @FXML
    private Pane ledDisplayPane;

    @FXML
    private Pane debugDisplayPane;

    @FXML
    private Label instanceUseCount;

    @FXML
    private Label displayRegisterX;

    @FXML
    private Label displayExponent;

    @FXML
    private Label labelExponent;

    @FXML
    private Label displayRegisterLastX;

    @FXML
    private Label displayRegisterY;

    @FXML
    private Label displayRegisterZ;

    @FXML
    private Label displayRegisterT;

    @FXML
    private Label displayRegisterS;

    @FXML
    private Label indAutoMode;

    @FXML
    private Label indFixMode;

    @FXML
    private Label indFloatMode;

    @FXML
    private Label indEngMode;

    @FXML
    private Label indF;

    @FXML
    private Label indDegMode;

    @FXML
    private Label indRadMode;

    /**
     *
     */
    private void refreshView() {

        LOGGER.debug("begin");

        MtCalculator calculator;
        calculator = MtCalculator.getInstance();

        instanceUseCount.textProperty().setValue(Long.toString(calculator.getInstanceUseCount()));

        indDegMode.setVisible(calculator.isDegMode());
        indRadMode.setVisible(calculator.isRadMode());

        indAutoMode.setVisible(calculator.isAutoMode());
        indFixMode.setText(calculator.getFixModeLabel());
        indFixMode.setVisible(calculator.isFixMode());
        indFloatMode.setVisible(calculator.isFloatMode());
        indEngMode.setVisible(calculator.isEngMode());

        indF.setVisible(calculator.isIndF());

        displayRegisterX.textProperty().setValue(calculator.registerXFormatedString());
        displayRegisterLastX.textProperty().setValue(calculator.registerLastXFormatedString());
        displayRegisterY.textProperty().setValue(calculator.registerYFormatedString());
        displayRegisterZ.textProperty().setValue(calculator.registerZFormatedString());
        displayRegisterT.textProperty().setValue(calculator.registerTFormatedString());
        displayRegisterS.textProperty().setValue(calculator.registerSFormatedString());

        displayExponent.textProperty().setValue(calculator.exponentFormatedString());
        displayExponent.setVisible(calculator.isExponentPressed());
        labelExponent.setVisible(calculator.isExponentPressed());

        LedDisplay ledDisplay = new LedDisplay();
        ledDisplay.displayNumberOnLedDisplay(calculator.registerXFormatedString(), ledDisplayPane);

        normalDisplayPane.setVisible(!calculator.isDebugMode());
        debugDisplayPane.setVisible(calculator.isDebugMode());

        LOGGER.debug("end");
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonDegAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonDeg();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRadAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRad();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonF();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEEXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEEX();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCLXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCLX();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCLRAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCLR();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton0Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton0();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton1Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton1();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton2Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton2();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton3Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton3();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton4Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton4();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton5Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton5();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton6Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton6();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton7Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton7();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton8Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton8();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton9Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton9();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCHSAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCHS();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonDecimalPointAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonDecimalPoint();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonAddAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonAdd();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSubtractAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSubtract();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonMultiplyAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonMultiply();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonDivideAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonDivide();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEnterAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEnter();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRollDownAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRollDown();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRollUpAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRollUp();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSwapXandYAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSwapXandY();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSTOAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSTO();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRCLAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRCL();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonLastXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonLastX();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonAutoAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonAuto();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFixAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonFix();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFloatAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonFloat();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEngAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEng();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonResetAction(ActionEvent event) {
        MtCalculator.pressButtonReset();
        refreshView();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonOffAction(ActionEvent event) {
        Platform.exit();
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshView();
    }

}
