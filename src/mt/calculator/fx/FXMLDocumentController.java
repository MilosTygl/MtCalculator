package mt.calculator.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import mt.calculator.hw.LedDigit;

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
    private Pane displayPane;

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
        displayNumberOnLedDisplay();
        normalDisplayPane.setVisible(!calculator.isDebugMode());
        debugDisplayPane.setVisible(calculator.isDebugMode());
        LOGGER.debug("end");
    }

    private String displayString;

    /**
     *
     * @param pos
     * @return
     */
    private char digitFromDisplayString(int pos) {
        if (pos < 0) {
            return ' ';
        }
        if (displayString == null) {
            return ' ';
        }
        if (displayString.length() == 0) {
            return ' ';
        }
        if (displayString.length() < pos + 1) {
            return ' ';
        }
        char displayDigit = displayString.charAt(pos);
        return displayDigit;
    }

    /**
     *
     */
    private void displayNumberOnLedDisplay() {
        ObservableList observableListDisplayPane;
        observableListDisplayPane = displayPane.getChildren();
        Object[] objectDisplayPane;
        objectDisplayPane = observableListDisplayPane.toArray();
        int displayStringIndex;
        displayStringIndex = -1;

        for (Object objectDigitPane : objectDisplayPane) {
            Pane digitPane;
            digitPane = (Pane) objectDigitPane;
            ObservableList observableListDigitPane;
            observableListDigitPane = digitPane.getChildren();
            Object[] objectDigitSegment;
            objectDigitSegment = observableListDigitPane.toArray();

            Shape shapeDigitSegmentA;
            shapeDigitSegmentA = (Shape) objectDigitSegment[0];
            Shape shapeDigitSegmentB;
            shapeDigitSegmentB = (Shape) objectDigitSegment[1];
            Shape shapeDigitSegmentC;
            shapeDigitSegmentC = (Shape) objectDigitSegment[2];
            Shape shapeDigitSegmentD;
            shapeDigitSegmentD = (Shape) objectDigitSegment[3];
            Shape shapeDigitSegmentE;
            shapeDigitSegmentE = (Shape) objectDigitSegment[4];
            Shape shapeDigitSegmentF;
            shapeDigitSegmentF = (Shape) objectDigitSegment[5];
            Shape shapeDigitSegmentG;
            shapeDigitSegmentG = (Shape) objectDigitSegment[6];
            Shape shapeDigitSegmentP;
            shapeDigitSegmentP = (Shape) objectDigitSegment[7];

            displayStringIndex++;
            char cx;
            cx = digitFromDisplayString(displayStringIndex);
            LedDigit ledDigit;
            ledDigit = new LedDigit(cx);

            shapeDigitSegmentA.setVisible(ledDigit.isSegmentA());
            shapeDigitSegmentB.setVisible(ledDigit.isSegmentB());
            shapeDigitSegmentC.setVisible(ledDigit.isSegmentC());
            shapeDigitSegmentD.setVisible(ledDigit.isSegmentD());
            shapeDigitSegmentE.setVisible(ledDigit.isSegmentE());
            shapeDigitSegmentF.setVisible(ledDigit.isSegmentF());
            shapeDigitSegmentG.setVisible(ledDigit.isSegmentG());
            shapeDigitSegmentP.setVisible(ledDigit.isSegmentP());
        }
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
