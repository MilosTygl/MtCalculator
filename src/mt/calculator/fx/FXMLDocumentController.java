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

    private String displayString;

    /**
     *
     */
    private void refreshLabel() {
        LOGGER.debug("begin");
        MtCalculator calculator;
        calculator = MtCalculator.getInstance();
        instanceUseCount.textProperty().setValue(Long.toString(calculator.getInstanceUseCount()));
        displayString = calculator.registerXFormatedString();
        displayRegisterX.textProperty().setValue(calculator.registerXFormatedString());
        displayExponent.textProperty().setValue(calculator.exponentFormatedString());
        displayExponent.setVisible(calculator.isExponentPressed());
        labelExponent.setVisible(calculator.isExponentPressed());
        displayRegisterLastX.textProperty().setValue(calculator.registerLastXFormatedString());
        displayRegisterY.textProperty().setValue(calculator.registerYFormatedString());
        displayRegisterZ.textProperty().setValue(calculator.registerZFormatedString());
        displayRegisterT.textProperty().setValue(calculator.registerTFormatedString());
        displayRegisterS.textProperty().setValue(calculator.registerSFormatedString());
        indAutoMode.setVisible(calculator.isAutoMode());
        indFixMode.setText(calculator.getFixModeLabel());
        indFixMode.setVisible(calculator.isFixMode());
        indFloatMode.setVisible(calculator.isFloatMode());
        indEngMode.setVisible(calculator.isEngMode());
        indF.setVisible(calculator.isIndF());
        indDegMode.setVisible(calculator.isDegMode());
        indRadMode.setVisible(calculator.isRadMode());
        LedDisplay ledDisplay = new LedDisplay();
        ledDisplay.displayNumberOnLedDisplay(displayString, ledDisplayPane);
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
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRadAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRad();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonF();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEEXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEEX();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCLXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCLX();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCLRAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCLR();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton0Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton0();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton1Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton1();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton2Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton2();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton3Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton3();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton4Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton4();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton5Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton5();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton6Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton6();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton7Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton7();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton8Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton8();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton9Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton9();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCHSAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCHS();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonDecimalPointAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonDecimalPoint();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonAddAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonAdd();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSubtractAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSubtract();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonMultiplyAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonMultiply();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonDivideAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonDivide();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEnterAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEnter();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRollDownAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRollDown();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRollUpAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRollUp();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSwapXandYAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSwapXandY();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSTOAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSTO();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRCLAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRCL();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonLastXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonLastX();
        refreshLabel();
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
     * @param event
     */
    @FXML
    private void handleButtonResetAction(ActionEvent event) {
        MtCalculator.pressButtonReset();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonAutoAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonAuto();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFixAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonFix();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFloatAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonFloat();
        refreshLabel();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEngAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEng();
        refreshLabel();
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshLabel();
    }

}
