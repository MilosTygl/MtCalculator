package mt.calculator.hw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import mt.calculator.utils.properties.PropertyFileUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class MtCalculator {

    private static final Logger LOGGER = Logger.getLogger(MtCalculator.class);

    private static MtCalculator INSTANCE;

    private long instanceUseCount;

    private MtRegister registerX;
    private MtRegister exponent;
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
    private boolean exponentPressed;
    private int exponentPositions;
    private boolean debugMode;

    /**
     *
     */
    private MtCalculator() {
        LOGGER.debug("construtcor");
        this.instanceUseCount = 0;
        this.eraseDisplay = false;
        this.registerX = new MtRegister();
        this.exponent = new MtRegister();
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
        this.exponentPressed = false;
        this.exponentPositions = 0;
        this.debugMode = getPropertyDebugMode();
    }

    /**
     *
     * @return
     */
    private boolean getPropertyDebugMode() {
        boolean propertyDebugMode;
        String keyDebugMode;
        String valueDebugMode;
        String valueDebugModeYes;
        String valueDebugModeNo;
        propertyDebugMode = false;
        PropertyFileUtils propertyFileUtils;
        propertyFileUtils = PropertyFileUtils.getInstance();
        valueDebugModeYes = "yes";
        valueDebugModeNo = "no";
        keyDebugMode = "debugMode";
        valueDebugMode = propertyFileUtils.getProperty(keyDebugMode);
        if (valueDebugMode == null) {
            valueDebugMode = valueDebugModeNo;
        }
        if (valueDebugMode.equalsIgnoreCase(valueDebugModeYes)) {
            propertyDebugMode = true;
        }
        return propertyDebugMode;
    }

    /**
     *
     * @return
     */
    public static MtCalculator getInstance() {

        LOGGER.debug("getInstance");

        if (INSTANCE == null) {
            INSTANCE = new MtCalculator();
            LOGGER.debug("New instance");
        } else {
            LOGGER.debug("Old instance");
        }

        INSTANCE.instanceUseCount++;
        return INSTANCE;
    }

    /**
     *
     * @return
     */
    public boolean isDebugMode() {
        return debugMode;
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
    public MtRegister getExponent() {
        return exponent;
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

    /**
     *
     */
    public void pressButtonDeg() {
        radMode = false;
        degMode = true;
    }

    /**
     *
     */
    public void pressButtonRad() {
        degMode = false;
        radMode = true;
    }

    /**
     *
     */
    private void pressButtonCLEX() {
        indF = false;
        exponentPositions = 0;
        exponent = new MtRegister();
    }

    /**
     *
     */
    private void releaseButtonEEX() {
        if (!exponentPressed) {
            return;
        }
        exponentPressed = false;
        exponentPositions = 0;
        if (exponent.getNumber().getNumber().intValue() == 0) {
            return;
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.tenPowX(exponent.getNumber()));
        register.setNumber(MtNumber.multiply(registerX.getNumber(), register.getNumber()));
        registerX = register;
        exponent = new MtRegister();
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonEEX() {
        if (indF) {
            pressButtonCLEX();
            return;
        }
        if (!exponentPressed) {
            exponentPressed = true;
            exponentPositions = 1;
            return;
        }
        releaseButtonEEX();
    }

    /**
     *
     * @return
     */
    public boolean isExponentPressed() {
        return exponentPressed;
    }

    /**
     *
     */
    public void pressButtonCLR() {
        if (indF) {
            pressButtonReciprocal();
            return;
        }
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
        if (indF) {
            pressButtonCLS();
            return;
        }
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
    private void pressButtonDigitExponent(long digit) {
        if (exponentPositions > 2) {
            return;
        }
        if (exponentPositions == 1) {
            exponent.setNumber(new MtNumber(BigDecimal.valueOf(digit)));
            exponentPositions++;
            return;
        }
        long exp = exponent.getNumber().getNumber().longValue();
        exp = exp * 10 + digit;
        exponent.setNumber(new MtNumber(BigDecimal.valueOf(exp)));
        exponentPositions++;
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
        if (exponentPressed) {
            pressButtonDigitExponent(digit);
            return;
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
        if (indF) {
            pressButtonArcSin();
            return;
        }
        pressButtonDigit(1L);
    }

    /**
     *
     */
    public void pressButton2() {
        if (indF) {
            pressButtonArcCos();
            return;
        }
        pressButtonDigit(2L);
    }

    /**
     *
     */
    public void pressButton3() {
        if (indF) {
            pressButtonArcTan();
            return;
        }
        pressButtonDigit(3L);
    }

    /**
     *
     */
    public void pressButton4() {
        if (indF) {
            pressButtonSin();
            return;
        }
        pressButtonDigit(4L);
    }

    /**
     *
     */
    public void pressButton5() {
        if (indF) {
            pressButtonCos();
            return;
        }
        pressButtonDigit(5L);
    }

    /**
     *
     */
    public void pressButton6() {
        if (indF) {
            pressButtonTan();
            return;
        }
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
            pressButtonTenPowerX();
            return;
        }
        pressButtonDigit(9L);

    }

    /**
     *
     */
    private void pressButtonPi() {
        indF = false;
        releaseButtonEEX();
        registerLastX = registerX;
        if (forceRaiseStack) {
            forceRaiseStack = false;
            raiseStack();
        }
        registerX = new MtRegister();
        registerX.setNumber(MtNumber.PI);
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
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
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.add(registerY.getNumber(), registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonSin() {
        indF = false;
        releaseButtonEEX();
        MtNumber wrkNumber;
        wrkNumber = registerX.getNumber();
        if (degMode) {
            wrkNumber = MtNumber.degToRad(wrkNumber);
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.sin(wrkNumber));
        registerLastX = registerX;
        registerX = register;
        registerT = registerZ;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonArcSin() {
        indF = false;
        releaseButtonEEX();
        MtNumber wrkNumber;
        wrkNumber = MtNumber.arcSin(registerX.getNumber());
        if (degMode) {
            wrkNumber = MtNumber.radToDeg(wrkNumber);
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(wrkNumber);
        registerLastX = registerX;
        registerX = register;
        registerT = registerZ;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonCos() {
        indF = false;
        releaseButtonEEX();
        MtNumber wrkNumber;
        wrkNumber = registerX.getNumber();
        if (degMode) {
            wrkNumber = MtNumber.degToRad(wrkNumber);
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.cos(wrkNumber));
        registerLastX = registerX;
        registerX = register;
        registerT = registerZ;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonArcCos() {
        indF = false;
        releaseButtonEEX();
        MtNumber wrkNumber;
        wrkNumber = MtNumber.arcCos(registerX.getNumber());
        if (degMode) {
            wrkNumber = MtNumber.radToDeg(wrkNumber);
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(wrkNumber);
        registerLastX = registerX;
        registerX = register;
        registerT = registerZ;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonTan() {
        indF = false;
        releaseButtonEEX();
        MtNumber wrkNumber;
        wrkNumber = registerX.getNumber();
        if (degMode) {
            wrkNumber = MtNumber.degToRad(wrkNumber);
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.tan(wrkNumber));
        registerLastX = registerX;
        registerX = register;
        registerT = registerZ;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonArcTan() {
        indF = false;
        releaseButtonEEX();
        MtNumber wrkNumber;
        wrkNumber = MtNumber.arcTan(registerX.getNumber());
        if (degMode) {
            wrkNumber = MtNumber.radToDeg(wrkNumber);
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(wrkNumber);
        registerLastX = registerX;
        registerX = register;
        registerT = registerZ;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonLn() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.ln(registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonEPowX() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.ePowX(registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonLog() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.log(registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonTenPowerX() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.tenPowX(registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        eraseDisplay = true;
        autoEnter = true;
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
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.subtract(registerY.getNumber(), registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
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
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.multiply(registerY.getNumber(), registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
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
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.divide(registerY.getNumber(), registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
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
        if (exponentPressed) {
            MtRegister wrkExponent;
            wrkExponent = new MtRegister();
            wrkExponent.setNumber(MtNumber.multiply(exponent.getNumber(), new MtNumber(-1L)));
            exponent = wrkExponent;
            return;
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.multiply(registerX.getNumber(), new MtNumber(-1L)));
        registerLastX = registerX;
        registerX = register;
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
        if (indF) {
            indF = false;
            debugMode = !debugMode;
            return;
        }
        releaseButtonEEX();
        raiseStack();
        forceRaiseStack = false;
        eraseDisplay = true;
    }

    /**
     *
     */
    public void pressButtonDROP() {
        indF = false;
        // TODO Implement the logic here
    }

    /**
     *
     */
    public void pressButtonRollDown() {
        if (indF) {
            pressButtonDROP();
            return;
        }
        MtRegister register;
        register = registerX;
        registerX = registerY;
        registerY = registerZ;
        registerZ = registerT;
        registerT = register;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonDUP() {
        indF = false;
        // TODO Implement the logic here
    }

    /**
     *
     */
    public void pressButtonRollUp() {
        if (indF) {
            pressButtonDUP();
            return;
        }
        MtRegister register;
        register = registerT;
        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerX = register;
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
    private void pressButtonYpowerX() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.pow(registerY.getNumber(), registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonSTO() {
        if (indF) {
            pressButtonYpowerX();
            return;
        }
        releaseButtonEEX();
        forceRaiseStack = false;
        registerS = registerX;
        eraseDisplay = true;
    }

    /**
     *
     */
    private void pressButtonXpowerY() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.pow(registerX.getNumber(), registerY.getNumber()));
        registerLastX = registerX;
        registerX = register;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonRCL() {
        if (indF) {
            pressButtonXpowerY();
            return;
        }
        registerLastX = registerX;
        raiseStack();
        registerX = registerS;
        eraseDisplay = true;
    }

    /**
     *
     */
    private void pressButtonSquareRoot() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.squareRoot(registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonLastX() {
        if (indF) {
            pressButtonSquareRoot();
            return;
        }
        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerX = registerLastX;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonReciprocal() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.ONE);
        register.setNumber(MtNumber.divide(register.getNumber(), registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    private void pressButtonSquare() {
        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.multiply(registerX.getNumber(), registerX.getNumber()));
        registerLastX = registerX;
        registerX = register;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;
    }

    /**
     *
     */
    public void pressButtonSwapXandY() {
        if (indF) {
            pressButtonSquare();
            return;
        }
        registerLastX = registerX;
        MtRegister register;
        register = registerX;
        registerX = registerY;
        registerY = register;
        eraseDisplay = true;
        forceRaiseStack = true;
    }

    /**
     * Set all display mode indicators to their defaults
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
        decimalFormatString = "0.000000000000E00";
        return decimalFormatString;

    }

    /**
     *
     * @return
     */
    private String getEngModeDecimalFormatString() {
        String decimalFormatString;
        decimalFormatString = "##0.000000000000E00";
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
        maxDigits = 20;
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
        } else {
            string = string.replaceFirst("E", "E ");
        }
        if (string.contains("E")) {
            int i;
            i = string.indexOf("E");
            String stringMantissa;
            stringMantissa = string.substring(0, 12);
            String stringExponent;
            stringExponent = string.substring(i + 1);
            string = stringMantissa + stringExponent;
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

    /**
     *
     * @return
     */
    public String exponentFormatedString() {
        String string;
        string = exponent.getNumber().getNumber().toPlainString();
        final int maxDigits;
        maxDigits = 3;
        if (string.length() > maxDigits) {
            string = string.substring(0, maxDigits);
        }
        return string;
    }
}
