package mt.calculator.hw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

import mt.calculator.utils.properties.PropertyAccess;
import mt.calculator.utils.properties.PropertyFileUtils;

import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class MtCalculator {

    private static final String LOG_BEGIN = "begin";
    private static final String LOG_END = "end";
    private static final String LOG_RETURN_1 = "return 1";
    private static final String LOG_RETURN_2 = "return 2";

    private static final Logger LOGGER = Logger.getLogger(MtCalculator.class);

    private static MtCalculator instance;

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

        LOGGER.debug(LOG_BEGIN);

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
        this.debugMode = PropertyAccess.getPropertyDebugMode();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @return
     */
    public static MtCalculator getInstance() {

        LOGGER.debug("getInstance");

        if (instance == null) {
            instance = new MtCalculator();
            LOGGER.debug("New instance");
        } else {
            LOGGER.debug("Old instance");
        }

        instance.instanceUseCount++;
        return instance;
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
    public boolean isNormalMode() {
        return !debugMode;
    }

    /**
     *
     * @return
     */
    public String getIndNormalDebugMode() {
        String indNormalDebugMode = "Debug";
        if (isDebugMode()) {
            indNormalDebugMode = "Normal";
        }
        return indNormalDebugMode;
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

        LOGGER.debug(LOG_BEGIN);

        indF = !indF;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonDeg() {

        LOGGER.debug(LOG_BEGIN);

        radMode = false;
        degMode = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonRad() {

        LOGGER.debug(LOG_BEGIN);

        degMode = false;
        radMode = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonCLEX() {

        LOGGER.debug(LOG_BEGIN);

        indF = false;
        exponentPositions = 0;
        exponent = new MtRegister();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void releaseButtonEEX() {

        LOGGER.debug(LOG_BEGIN);

        if (!exponentPressed) {
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        exponentPressed = false;
        exponentPositions = 0;
        if (exponent.getNumber().getNumber().intValue() == 0) {
            LOGGER.debug(LOG_RETURN_2);
            return;
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.tenPowX(exponent.getNumber()));
        register.setNumber(MtNumber.multiply(registerX.getNumber(), register.getNumber()));
        registerX = register;
        reformatRegisterX();
        exponent = new MtRegister();
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonEEX() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonCLEX();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        if (MtNumber.compare(registerX.getNumber(), MtNumber.ZERO) == 0) {
            registerX.setNumber(MtNumber.ONE);
        }
        if (!exponentPressed) {
            exponentPressed = true;
            exponentPositions = 1;
            LOGGER.debug(LOG_RETURN_2);
            return;
        }
        releaseButtonEEX();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @return
     */
    public boolean isExponentPressed() {

        LOGGER.debug(LOG_BEGIN);
        LOGGER.debug(LOG_END);

        return exponentPressed;
    }

    /**
     *
     */
    public void pressButtonCLR() {

        LOGGER.debug(LOG_BEGIN);

        registerX = new MtRegister();
        registerLastX = new MtRegister();
        registerY = new MtRegister();
        registerZ = new MtRegister();
        registerT = new MtRegister();
        decimalPointPressed = false;
        decimalPositions = 0;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonCLX() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonCLS();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        registerX = new MtRegister();
        forceRaiseStack = false;
        decimalPointPressed = false;
        decimalPositions = 0;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigitFixMode(long digit) {

        LOGGER.debug(LOG_BEGIN);

        fixMode1 = false;
        fixModeSize = digit;
        fixModeLabel = "Fix " + Long.toString(digit);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigitExponent(long digit) {

        LOGGER.debug(LOG_BEGIN);

        if (exponentPositions > 2) {
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        if (MtNumber.compare(registerX.getNumber(), MtNumber.ZERO) == 0) {
            registerX.setNumber(MtNumber.ONE);
        }
        if (exponentPositions == 1) {
            exponent.setNumber(new MtNumber(BigDecimal.valueOf(digit)));
            exponentPositions++;
            LOGGER.debug(LOG_RETURN_2);
            return;
        }
        long exp = exponent.getNumber().getNumber().longValue();
        exp = exp * 10 + digit;
        exponent.setNumber(new MtNumber(BigDecimal.valueOf(exp)));
        exponentPositions++;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigitData(long digit) {

        LOGGER.debug(LOG_BEGIN);

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
            LOGGER.debug(LOG_RETURN_1);
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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigit(long digit) {

        LOGGER.debug(LOG_BEGIN);

        if (fixMode1) {
            pressButtonDigitFixMode(digit);
        } else {
            forceRaiseStack = true;
            pressButtonAuto();
            pressButtonDigitData(digit);
        }

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton0() {

        LOGGER.debug(LOG_BEGIN);

        pressButtonDigit(0L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton1() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonArcSin();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(1L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton2() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonArcCos();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(2L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton3() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonArcTan();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(3L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton4() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonSin();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(4L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton5() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonCos();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(5L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton6() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonTan();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(6L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton7() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonEPowX();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(7L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton8() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonLog();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(8L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButton9() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonTenPowerX();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        pressButtonDigit(9L);

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonPi() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonDecimalPoint() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonPi();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        if (decimalPointPressed) {
            LOGGER.debug(LOG_RETURN_2);
            return;
        }
        if (autoEnter) {
            pressButtonEnter();
            autoEnter = false;
        }
        if (eraseDisplay) {
            registerX = new MtRegister();
            eraseDisplay = false;
            decimalPositions = 0;
        }
        decimalPointPressed = true;
        decimalPositions++;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonAdd() {

        LOGGER.debug(LOG_BEGIN);

        reformatRegisterX();
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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonSin() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonArcSin() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonCos() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonArcCos() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonTan() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonArcTan() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonLn() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonEPowX() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonLog() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonTenPowerX() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonSubtract() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonLn();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        reformatRegisterX();
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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonMultiply() {

        LOGGER.debug(LOG_BEGIN);

        reformatRegisterX();
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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonDivide() {

        LOGGER.debug(LOG_BEGIN);

        reformatRegisterX();
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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonCHS() {

        LOGGER.debug(LOG_BEGIN);

        if (exponentPressed) {
            MtRegister wrkExponent;
            wrkExponent = new MtRegister();
            wrkExponent.setNumber(MtNumber.multiply(exponent.getNumber(), new MtNumber(-1L)));
            exponent = wrkExponent;
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.multiply(registerX.getNumber(), new MtNumber(-1L)));
        registerLastX = registerX;
        registerX = register;
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void raiseStack() {

        LOGGER.debug(LOG_BEGIN);

        reformatRegisterX();

        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerLastX = registerX;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonEnter() {

        LOGGER.debug(LOG_BEGIN);
        if (indF) {
            indF = false;
            debugMode = !debugMode;
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        decimalPointPressed = false;
        releaseButtonEEX();
        raiseStack();
        forceRaiseStack = false;
        eraseDisplay = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonDROP() {

        LOGGER.debug(LOG_BEGIN);

        indF = false;
        registerX = registerY;
        registerY = registerZ;
        registerZ = registerT;
        registerT = new MtRegister();
        eraseDisplay = true;
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonRollDown() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonDROP();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        reformatRegisterX();
        MtRegister register;
        register = registerX;
        registerX = registerY;
        registerY = registerZ;
        registerZ = registerT;
        registerT = register;
        eraseDisplay = true;
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonDUP() {

        LOGGER.debug(LOG_BEGIN);

        indF = false;
        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        eraseDisplay = true;
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonRollUp() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonDUP();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        reformatRegisterX();
        MtRegister register;
        register = registerT;
        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerX = register;
        eraseDisplay = true;
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonCLS() {

        LOGGER.debug(LOG_BEGIN);

        indF = false;
        registerS = new MtRegister();

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonYpowerX() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonSTO() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonYpowerX();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        reformatRegisterX();
        releaseButtonEEX();
        forceRaiseStack = false;
        registerS = registerX;
        eraseDisplay = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonXpowerY() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonRCL() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonXpowerY();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }
        registerLastX = registerX;
        raiseStack();
        registerX = registerS;
        eraseDisplay = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonSquareRoot() {

        LOGGER.debug(LOG_BEGIN);

        if (indF) {
            pressButtonSquare();
            LOGGER.debug(LOG_RETURN_1);
            return;
        }

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonLastX() {

        LOGGER.debug(LOG_BEGIN);

        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerX = registerLastX;
        eraseDisplay = true;
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonReciprocal() {

        LOGGER.debug(LOG_BEGIN);

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

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    private void pressButtonSquare() {

        LOGGER.debug(LOG_BEGIN);

        indF = false;
        releaseButtonEEX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.powN(registerX.getNumber(), 2));
        registerLastX = registerX;
        registerX = register;
        registerY = registerZ;
        registerZ = registerT;
        eraseDisplay = true;
        autoEnter = true;
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonSwapXandY() {

        LOGGER.debug(LOG_BEGIN);

        reformatRegisterX();
        registerLastX = registerX;
        MtRegister register;
        register = registerX;
        registerX = registerY;
        registerY = register;
        eraseDisplay = true;
        forceRaiseStack = true;

        LOGGER.debug(LOG_END);
    }

    /**
     * Set all display mode indicators to their defaults
     */
    private void clearMode() {

        LOGGER.debug(LOG_BEGIN);

        autoMode = false;
        fixMode = false;
        fixMode1 = false;
        floatMode = false;
        engMode = false;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public static void reset() {

        LOGGER.debug(LOG_BEGIN);

        PropertyFileUtils.reset();
        instance = null;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonAuto() {

        LOGGER.debug(LOG_BEGIN);

        clearMode();
        autoMode = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonFix() {

        LOGGER.debug(LOG_BEGIN);

        clearMode();
        fixMode = true;
        fixMode1 = true;
        fixModeSize = 0;
        fixModeLabel = "Fix ?";

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonFloat() {

        LOGGER.debug(LOG_BEGIN);

        clearMode();
        floatMode = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     */
    public void pressButtonEng() {

        LOGGER.debug(LOG_BEGIN);

        clearMode();
        engMode = true;

        LOGGER.debug(LOG_END);
    }

    /**
     *
     * @return
     */
    private String getFixModeDecimalFormatString() {

        LOGGER.debug(LOG_BEGIN);

        String decimalFormatString;
        decimalFormatString = "0000000000000";
        int decimalPointPosition;
        decimalPointPosition = (int) (decimalFormatString.length() - fixModeSize);
        decimalFormatString = decimalFormatString.substring(0, decimalPointPosition) + "." + decimalFormatString.substring(decimalPointPosition);
        for (int i = 1; i < decimalPointPosition; i++) {
            decimalFormatString = decimalFormatString.replaceFirst("0", "#");
        }

        LOGGER.debug(LOG_END);

        return decimalFormatString;
    }

    /**
     *
     * @return
     */
    private String getFloatModeDecimalFormatString() {

        LOGGER.debug(LOG_BEGIN);

        final String decimalFormatString;
        decimalFormatString = "0.000000000000E00";

        LOGGER.debug(LOG_END);

        return decimalFormatString;

    }

    /**
     *
     * @return
     */
    private String getEngModeDecimalFormatString() {

        LOGGER.debug(LOG_BEGIN);

        final String decimalFormatString;
        decimalFormatString = "##0.000000000000E00";

        LOGGER.debug(LOG_END);

        return decimalFormatString;
    }

    /**
     *
     * @param register
     * @return
     */
    private String registerFormatedStringAutoMode(MtRegister register) {

        LOGGER.debug(LOG_BEGIN);

        String string;
        string = register.getNumber().getNumber().toPlainString();
        final int maxDigits;
        maxDigits = 16;
        if (string.length() > maxDigits) {
            string = string.substring(0, maxDigits);
        }

        LOGGER.debug(LOG_END);

        return string;
    }

    /**
     *
     * @param register
     * @return
     */
    private String registerFormatedString(MtRegister register) {

        LOGGER.debug(LOG_BEGIN);

        if (register.getNumber().getNumber() == null) {
            LOGGER.debug("end1");
            return "Error";
        }

        if (autoMode && MtNumber.isOutOfFixedRange(register.getNumber())) {
            pressButtonFloat();
        }

        final String decimalFormatString;
        if (floatMode) {
            decimalFormatString = getFloatModeDecimalFormatString();
        } else if (engMode) {
            decimalFormatString = getEngModeDecimalFormatString();
        } else if (fixMode) {
            decimalFormatString = getFixModeDecimalFormatString();
        } else { // default autoMode
            LOGGER.debug(LOG_RETURN_1);
            return registerFormatedStringAutoMode(register);
        }

        final DecimalFormat decimalFormat;
        decimalFormat = new DecimalFormat(decimalFormatString);
        String string;
        string = decimalFormat.format(register.getNumber().getNumber());
        if (!string.contains("E-")) {
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

        LOGGER.debug(LOG_END);

        return string;
    }

    /**
     *
     * @return
     */
    public String registerXFormatedString() {

        LOGGER.debug(LOG_BEGIN);

        String registerXFormatedString2;
        registerXFormatedString2 = registerFormatedString(registerX);

        if (autoMode && decimalPointPressed && decimalPositions <= 1) {
            registerXFormatedString2 = registerXFormatedString2.concat(".");
        }
        if (exponentPressed) {
            String registerXFormatedString3 = registerXFormatedString2.concat("               ");
            registerXFormatedString2 = registerXFormatedString3.substring(0, 12).concat(exponentFormatedString());
        }

        LOGGER.debug(LOG_END);

        return registerXFormatedString2;
    }

    /**
     *
     * @return
     */
    public String registerLastXFormatedString() {

        LOGGER.debug(LOG_BEGIN);

        final String s;
        s = registerFormatedString(registerLastX);

        LOGGER.debug(LOG_END);

        return s;
    }

    /**
     *
     * @return
     */
    public String registerYFormatedString() {

        LOGGER.debug(LOG_BEGIN);

        final String s;
        s = registerFormatedString(registerY);

        LOGGER.debug(LOG_END);

        return s;
    }

    /**
     *
     * @return
     */
    public String registerZFormatedString() {

        LOGGER.debug(LOG_BEGIN);

        final String s;
        s = registerFormatedString(registerZ);

        LOGGER.debug(LOG_END);

        return s;
    }

    /**
     *
     * @return
     */
    public String registerTFormatedString() {

        LOGGER.debug(LOG_BEGIN);

        final String s;
        s = registerFormatedString(registerT);

        LOGGER.debug(LOG_END);

        return s;
    }

    /**
     *
     * @return
     */
    public String registerSFormatedString() {

        LOGGER.debug(LOG_BEGIN);

        final String s;
        s = registerFormatedString(registerS);

        LOGGER.debug(LOG_END);

        return s;
    }

    /**
     *
     * @return
     */
    public String exponentFormatedString() {

        LOGGER.debug(LOG_BEGIN);

        final String decimalFormatString;
        decimalFormatString = "#00";

        DecimalFormat decimalFormat;
        decimalFormat = new DecimalFormat(decimalFormatString);

        String string;
        string = decimalFormat.format(exponent.getNumber().getNumber());

        if (string.length() < 3) {
            string = " " + string;
        }

        LOGGER.debug(LOG_END);

        return string;
    }

    /**
     *
     */
    private void reformatRegisterX() {

        LOGGER.debug(LOG_BEGIN);

        registerX.getNumber().stripTrailingZeroes();

        LOGGER.debug(LOG_END);
    }
}
