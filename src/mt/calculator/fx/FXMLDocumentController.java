package mt.calculator.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import mt.calculator.consts.Consts;
import mt.calculator.hw.MtCalculator;

import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class FXMLDocumentController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(FXMLDocumentController.class);

    private static final String LOG_BEGIN = Consts.getLogBegin();
    private static final String LOG_END = Consts.getLogEnd();

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

    @FXML
    private Label indNormalDebugMode;

    /**
     *
     */
    private void refreshView() {

        LOGGER.debug(LOG_BEGIN);

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

        normalDisplayPane.setVisible(calculator.isNormalMode());
        debugDisplayPane.setVisible(calculator.isDebugMode());

        indNormalDebugMode.textProperty().setValue(calculator.getIndNormalDebugMode());

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonDegAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonDeg();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRadAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonRad();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonF();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonReciprocalAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonReciprocal();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSquareRootAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonSquareRoot();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEEXAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonEEX();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCLXAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonCLX();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCLRAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonCLR();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton0Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton0();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton1Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton1();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton2Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton2();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton3Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton3();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton4Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton4();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton5Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton5();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton6Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton6();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton7Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton7();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton8Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton8();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButton9Action(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButton9();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonCHSAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonCHS();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonDecimalPointAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonDecimalPoint();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonAddAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonAdd();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSubtractAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonSubtract();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonMultiplyAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonMultiply();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonDivideAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonDivide();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEnterAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonEnter();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRollDownAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonRollDown();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRollUpAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonRollUp();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSwapXandYAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonSwapXandY();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonSTOAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonSTO();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonRCLAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonRCL();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonLastXAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonLastX();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonAutoAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonAuto();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFixAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonFix();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonFloatAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonFloat();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonEngAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.getInstance().pressButtonEng();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonResetAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        MtCalculator.reset();
        refreshView();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonOffAction(ActionEvent event) {

        LOGGER.debug(LOG_BEGIN);

        Platform.exit();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LOGGER.debug(LOG_BEGIN);

        refreshView();

        LOGGER.debug(LOG_END);
    }

}
