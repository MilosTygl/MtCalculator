package mt.calculator.hw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import org.apache.log4j.Logger;

public class MtCalculator {

    private static final Logger LOGGER = Logger.getLogger(MtCalculator.class);

    private static MtCalculator INSTANCE;

    private long instanceUseCount;

    private MtRegister registerX;
    private MtRegister registerLastX;
    private MtRegister registerY;
    private MtRegister registerZ;
    private MtRegister registerT;
    private MtRegister registerS;

    private boolean autoEnter;

    private boolean autoMode;
    private boolean fixMode;
    private boolean fixMode1;
    private long fixModeSize;
    private String fixModeLabel;
    private boolean floatMode;
    private boolean engMode;
    private boolean indF;
    private boolean forceRaiseStack;
    private boolean degMode;
    private boolean radMode;
    private boolean eraseDisplay;
    private boolean decimalPointPressed;
    private int decimalPositions;

    /**
     *
     */
    private MtCalculator() {
        LOGGER.debug("construtcor");
        this.instanceUseCount = 0;
        this.eraseDisplay = false;
        this.registerX = new MtRegister();
        this.registerLastX = new MtRegister();
        this.registerY = new MtRegister();
        this.registerZ = new MtRegister();
        this.registerT = new MtRegister();
        this.registerS = new MtRegister();
        this.autoEnter = false;
        this.autoMode = true;
        this.fixMode = false;
        this.fixMode1 = false;
        this.floatMode = false;
        this.engMode = false;
        this.indF = false;
        this.degMode = true;
        this.radMode = false;
        this.forceRaiseStack = true;
        this.decimalPointPressed = false;
        this.decimalPositions = 0;
    }

    /**
     *
     * @return
     */
    public static MtCalculator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MtCalculator();
        }
        INSTANCE.instanceUseCount++;
        return INSTANCE;
    }

    public void refreshStatus() {
        // @TODO Implement logic here
    }

    /**
     *
     * @return
     */
    public MtRegister getRegisterX() {
        return registerX;
    }

    /**
     *
     * @return
     */
    public MtRegister getRegisterLastX() {
        return registerLastX;
    }

    /**
     *
     * @return
     */
    public MtRegister getRegisterY() {
        return registerY;
    }

    /**
     *
     * @return
     */
    public MtRegister getRegisterZ() {
        return registerZ;
    }

    /**
     *
     * @return
     */
    public MtRegister getRegisterT() {
        return registerT;
    }

    /**
     *
     * @return
     */
    public MtRegister getRegisterS() {
        return registerS;
    }

    /**
     *
     * @return
     */
    public boolean isDegMode() {
        return degMode;
    }

    /**
     *
     * @return
     */
    public boolean isRadMode() {
        return radMode;
    }

    /**
     *
     * @return
     */
    public boolean isAutoMode() {
        return autoMode;
    }

    /**
     *
     * @return
     */
    public boolean isFixMode() {
        return fixMode;
    }

    /**
     *
     * @return
     */
    public boolean isFixMode1() {
        return fixMode1;
    }

    /**
     *
     * @return
     */
    public boolean isFloatMode() {
        return floatMode;
    }

    /**
     *
     * @return
     */
    public boolean isEngMode() {
        return engMode;
    }

    /**
     *
     * @return
     */
    public long getFixModeSize() {
        return fixModeSize;
    }

    /**
     *
     * @return
     */
    public String getFixModeLabel() {
        return fixModeLabel;
    }

    /**
     *
     * @return
     */
    public long getInstanceUseCount() {
        return instanceUseCount;
    }

    /**
     *
     * @return
     */
    public boolean isIndF() {
        return indF;
    }

    /**
     *
     */
    public void pressButtonF() {
        indF = !indF;
    }

    public void pressButtonDeg() {
        radMode = false;
        degMode = true;
    }

    public void pressButtonRad() {
        degMode = false;
        radMode = true;
    }

    /**
     *
     */
    public void pressButtonEEX() {
        // @TODO Implement logic here
    }

    /**
     *
     */
    public void pressButtonCLR() {
        registerX = new MtRegister();
        registerLastX = new MtRegister();
        registerY = new MtRegister();
        registerZ = new MtRegister();
        registerT = new MtRegister();
        decimalPointPressed = false;
        decimalPositions = 0;
    }

    /**
     *
     */
    public void pressButtonCLX() {
        registerX = new MtRegister();
        forceRaiseStack = false;
        decimalPointPressed = false;
        decimalPositions = 0;
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigitFixMode(long digit) {
        fixMode1 = false;
        fixModeSize = digit;
        fixModeLabel = "Fix " + Long.toString(digit);
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigitData(long digit) {
        if (autoEnter) {
            pressButtonEnter();
            autoEnter = false;
        }
        if (eraseDisplay) {
            registerX = new MtRegister();
            eraseDisplay = false;
            decimalPositions = 0;
            decimalPointPressed = false;
        }
        MtNumber number;
        number = registerX.getNumber();
        BigDecimal bd;
        if (!decimalPointPressed) {
            number = MtNumber.multiply(number, MtNumber.TEN);
            bd = new BigDecimal(digit);
        } else {
            bd = new BigDecimal(new BigInteger(Long.toString(digit)), decimalPositions);
            decimalPositions++;
        }
        if (MtNumber.compare(number, MtNumber.ZERO) >= 0) {
            number = MtNumber.add(number, new MtNumber(bd));
        } else {
            number = MtNumber.subtract(number, new MtNumber(bd));
        }
        registerX.setNumber(number);
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigit(long digit) {
        if (fixMode1) {
            pressButtonDigitFixMode(digit);
        } else {
            forceRaiseStack = true;
            pressButtonDigitData(digit);
        }
    }

    /**
     *
     */
    public void pressButton0() {
        pressButtonDigit(0L);
    }

    /**
     *
     */
    public void pressButton1() {
        pressButtonDigit(1L);
    }

    /**
     *
     */
    public void pressButton2() {
        pressButtonDigit(2L);
    }

    /**
     *
     */
    public void pressButton3() {
        pressButtonDigit(3L);
    }

    /**
     *
     */
    public void pressButton4() {
        pressButtonDigit(4L);
    }

    /**
     *
     */
    public void pressButton5() {
        pressButtonDigit(5L);
    }

    /**
     *
     */
    public void pressButton6() {
        pressButtonDigit(6L);
    }

    /**
     *
     */
    public void pressButton7() {
        if (indF) {
            pressButtonEPowX();
            return;
        }
        pressButtonDigit(7L);
    }

    /**
     *
     */
    public void pressButton8() {
        if (indF) {
            pressButtonLog();
            return;
        }
        pressButtonDigit(8L);
    }

    /**
     *
     */
    public void pressButton9() {
        if (indF) {
            pressButtonTenPowX();
            return;
        }
        pressButtonDigit(9L);

    }

    /**
     *
     */
    private void pressButtonPi() {
        indF = false;
        if (forceRaiseStack) {
            forceRaiseStack = false;
            raiseStack();
        }
        registerX = new MtRegister();
        registerX.setNumber(MtNumber.PI);
        eraseDisplay = true;
        autoEnter = false;
    }

    /**
     *
     */
    public void pressButtonDecimalPoint() {
        if (indF) {
            pressButtonPi();
            return;
        }
        decimalPointPressed = true;
        decimalPositions++;
    }

    /**
     *
     */
    public void pressButtonAdd() {
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.add(registerY.getNumber(), registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonLn() {
        indF = false;
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.ln(registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonEPowX() {
        indF = false;
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.ePowX(registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonLog() {
        indF = false;
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.log(registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonTenPowX() {
        indF = false;
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.tenPowX(registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonSubtract() {
        if (indF) {
            pressButtonLn();
            return;
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.subtract(registerY.getNumber(), registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonMultiply() {
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.multiply(registerY.getNumber(), registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonDivide() {
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.divide(registerY.getNumber(), registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonCHS() {
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.multiply(registerX.getNumber(), new MtNumber(-1L)));
        registerX = register;
        registerLastX = registerX;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void raiseStack() {
        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerLastX = registerX;
    }

    /**
     *
     */
    public void pressButtonEnter() {
        raiseStack();
        forceRaiseStack = false;
        eraseDisplay = true;
    }

    /**
     *
     */
    public void pressButtonRollDown() {
        MtRegister register;
        register = registerX;
        registerX = registerY;
        registerY = registerZ;
        registerZ = registerT;
        registerT = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonRollUp() {
        MtRegister register;
        register = registerT;
        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerX = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonCLS() {
        indF = false;
        registerS = new MtRegister();
    }

    /**
     *
     */
    public void pressButtonSTO() {
        if (indF) {
            pressButtonCLS();
            return;
        }
        forceRaiseStack = false;
        registerS = registerX;
        eraseDisplay = true;
    }

    /**
     *
     */
    private void pressButtonPower() {
        indF = false;
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.pow(registerX.getNumber(), registerY.getNumber()));
        registerX = register;
        registerLastX = registerX;
        registerT = registerZ;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonRCL() {
        if (indF) {
            pressButtonPower();
            return;
        }
        if (forceRaiseStack) {
            forceRaiseStack = false;
            raiseStack();
        }
        registerX = registerS;
        eraseDisplay = true;
    }

    /**
     *
     */
    public void pressButtonSqrt() {
        indF = false;
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.sqrRoot(registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonLastX() {
        if (indF) {
            pressButtonSqrt();
            return;
        }
        registerX = registerLastX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonOneDivideByX() {
        indF = false;
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.ONE);
        register.setNumber(MtNumber.divide(register.getNumber(), registerX.getNumber()));
        registerX = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonSwapXandY() {
        if (indF) {
            pressButtonOneDivideByX();
            return;
        }
        MtRegister register;
        register = registerX;
        registerX = registerY;
        registerY = register;
        registerLastX = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void clearMode() {
        autoMode = false;
        fixMode = false;
        fixMode1 = false;
        floatMode = false;
        engMode = false;
    }

    /**
     *
     */
    public static void pressButtonReset() {
        INSTANCE = null;
    }

    /**
     *
     */
    public void pressButtonAuto() {
        clearMode();
        autoMode = true;
    }

    /**
     *
     */
    public void pressButtonFix() {
        clearMode();
        fixMode = true;
        fixMode1 = true;
        fixModeSize = 0;
        fixModeLabel = "Fix ?";
    }

    /**
     *
     */
    public void pressButtonFloat() {
        clearMode();
        floatMode = true;
    }

    /**
     *
     */
    public void pressButtonEng() {
        clearMode();
        engMode = true;
    }

    /**
     *
     * @return
     */
    private String getFixModeDecimalFormatString() {
        String decimalFormatString = "0000000000000";
        int decimalPointPosition;
        decimalPointPosition = (int) (decimalFormatString.length() - fixModeSize);
        decimalFormatString = decimalFormatString.substring(0, decimalPointPosition) + "." + decimalFormatString.substring(decimalPointPosition);
        for (int i = 1; i < decimalPointPosition; i++) {
            decimalFormatString = decimalFormatString.replaceFirst("0", "#");
        }
        return decimalFormatString;
    }

    /**
     *
     * @return
     */
    private String getFloatModeDecimalFormatString() {
        String decimalFormatString;
        decimalFormatString = "0.000000000E00";
        return decimalFormatString;

    }

    /**
     *
     * @return
     */
    private String getEngModeDecimalFormatString() {
        String decimalFormatString;
        decimalFormatString = "##0.0000000E00";
        return decimalFormatString;
    }

    /**
     *
     * @param register
     * @return
     */
    private String registerFormatedStringAutoMode(MtRegister register) {
        String string;
        string = register.getNumber().getNumber().toPlainString();
        final int maxDigits;
        maxDigits = 15;
        if (string.length() > maxDigits) {
            string = string.substring(0, maxDigits);
        }
        return string;
    }

    /**
     *
     * @param register
     * @return
     */
    private String registerFormatedString(MtRegister register) {
        String decimalFormatString;
        if (floatMode) {
            decimalFormatString = getFloatModeDecimalFormatString();
        } else if (engMode) {
            decimalFormatString = getEngModeDecimalFormatString();
        } else if (fixMode) {
            decimalFormatString = getFixModeDecimalFormatString();
        } else { // default autoMode
            return registerFormatedStringAutoMode(register);
        }
        DecimalFormat decimalFormat;
        decimalFormat = new DecimalFormat(decimalFormatString);
        String string;
        string = decimalFormat.format(register.getNumber().getNumber());
        if (string.contains("E-")) {
            string = string.replaceFirst("E", " ");
        } else {
            string = string.replaceFirst("E", "  ");
        }
        return string;
    }

    /**
     *
     * @return
     */
    public String registerXFormatedString() {
        return registerFormatedString(registerX);
    }

    /**
     *
     * @return
     */
    public String registerLastXFormatedString() {
        return registerFormatedString(registerLastX);
    }

    /**
     *
     * @return
     */
    public String registerYFormatedString() {
        return registerFormatedString(registerY);
    }

    /**
     *
     * @return
     */
    public String registerZFormatedString() {
        return registerFormatedString(registerZ);
    }

    /**
     *
     * @return
     */
    public String registerTFormatedString() {
        return registerFormatedString(registerT);
    }

    /**
     *
     * @return
     */
    public String registerSFormatedString() {
        return registerFormatedString(registerS);
    }
}
