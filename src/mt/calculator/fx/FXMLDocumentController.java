package mt.calculator.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import mt.calculator.hw.MtCalculator;
import org.apache.log4j.Logger;

public class FXMLDocumentController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(FXMLDocumentController.class);

    @FXML
    private Label instanceUseCount;

    @FXML
    private Label displayRegisterX;

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

    private void refreshLabel() {
        LOGGER.debug("begin");
        MtCalculator calculator;
        calculator = MtCalculator.getInstance();
//        calculator.refreshStatus();
        instanceUseCount.textProperty().setValue(Long.toString(calculator.getInstanceUseCount()));
        displayRegisterX.textProperty().setValue(calculator.registerXFormatedString());
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
        LOGGER.debug("end");
    }

    @FXML
    private void handleButtonDegAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonDeg();
        refreshLabel();
    }

    @FXML
    private void handleButtonRadAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRad();
        refreshLabel();
    }

    @FXML
    private void handleButtonFAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonF();
        refreshLabel();
    }

    @FXML
    private void handleButtonEEXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEEX();
        refreshLabel();
    }

    @FXML
    private void handleButtonCLXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCLX();
        refreshLabel();
    }

    @FXML
    private void handleButtonCLRAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCLR();
        refreshLabel();
    }

    @FXML
    private void handleButton0Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton0();
        refreshLabel();
    }

    @FXML
    private void handleButton1Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton1();
        refreshLabel();
    }

    @FXML
    private void handleButton2Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton2();
        refreshLabel();
    }

    @FXML
    private void handleButton3Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton3();
        refreshLabel();
    }

    @FXML
    private void handleButton4Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton4();
        refreshLabel();
    }

    @FXML
    private void handleButton5Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton5();
        refreshLabel();
    }

    @FXML
    private void handleButton6Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton6();
        refreshLabel();
    }

    @FXML
    private void handleButton7Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton7();
        refreshLabel();
    }

    @FXML
    private void handleButton8Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton8();
        refreshLabel();
    }

    @FXML
    private void handleButton9Action(ActionEvent event) {
        MtCalculator.getInstance().pressButton9();
        refreshLabel();
    }

    @FXML
    private void handleButtonCHSAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonCHS();
        refreshLabel();
    }

    @FXML
    private void handleButtonDecimalPointAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonDecimalPoint();
        refreshLabel();
    }

    @FXML
    private void handleButtonAddAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonAdd();
        refreshLabel();
    }

    @FXML
    private void handleButtonSubtractAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSubtract();
        refreshLabel();
    }

    @FXML
    private void handleButtonMultiplyAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonMultiply();
        refreshLabel();
    }

    @FXML
    private void handleButtonDivideAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonDivide();
        refreshLabel();
    }

    @FXML
    private void handleButtonEnterAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEnter();
        refreshLabel();
    }

    @FXML
    private void handleButtonRollDownAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRollDown();
        refreshLabel();
    }

    @FXML
    private void handleButtonRollUpAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRollUp();
        refreshLabel();
    }

    @FXML
    private void handleButtonSwapXandYAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSwapXandY();
        refreshLabel();
    }

    @FXML
    private void handleButtonSTOAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonSTO();
        refreshLabel();
    }

    @FXML
    private void handleButtonRCLAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonRCL();
        refreshLabel();
    }

    @FXML
    private void handleButtonLastXAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonLastX();
        refreshLabel();
    }

    @FXML
    private void handleButtonOffAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void handleButtonResetAction(ActionEvent event) {
        MtCalculator.pressButtonReset();
        refreshLabel();
    }

    @FXML
    private void handleButtonAutoAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonAuto();
        refreshLabel();
    }

    @FXML
    private void handleButtonFixAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonFix();
        refreshLabel();
    }

    @FXML
    private void handleButtonFloatAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonFloat();
        refreshLabel();
    }

    @FXML
    private void handleButtonEngAction(ActionEvent event) {
        MtCalculator.getInstance().pressButtonEng();
        refreshLabel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshLabel();
    }

}
