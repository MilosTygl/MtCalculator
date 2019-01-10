package mt.calculator.hw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import mt.calculator.utils.properties.PropertyAccess;
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

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
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

        LOGGER.debug("begin");

        indF = !indF;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonDeg() {

        LOGGER.debug("begin");

        radMode = false;
        degMode = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonRad() {

        LOGGER.debug("begin");

        degMode = false;
        radMode = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonCLEX() {

        LOGGER.debug("begin");

        indF = false;
        exponentPositions = 0;
        exponent = new MtRegister();

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void releaseButtonEEX() {

        LOGGER.debug("begin");

        if (!exponentPressed) {
            LOGGER.debug("end 1");
            return;
        }
        exponentPressed = false;
        exponentPositions = 0;
        if (exponent.getNumber().getNumber().intValue() == 0) {
            LOGGER.debug("end 2");
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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonEEX() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonCLEX();
            LOGGER.debug("end 1");
            return;
        }
//        if (MtNumber.compare(registerX.getNumber(), MtNumber.ZERO) == 0) {
//            registerX = new MtRegister();
//            registerX.setNumber(MtNumber.ONE);
//        }
        if (!exponentPressed) {
            exponentPressed = true;
            exponentPositions = 1;
            LOGGER.debug("end 2");
            return;
        }
        releaseButtonEEX();

        LOGGER.debug("end");
    }

    /**
     *
     * @return
     */
    public boolean isExponentPressed() {

        LOGGER.debug("begin");
        LOGGER.debug("end");

        return exponentPressed;
    }

    /**
     *
     */
    public void pressButtonCLR() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonReciprocal();
            LOGGER.debug("end 1");
            return;
        }
        registerX = new MtRegister();
        registerLastX = new MtRegister();
        registerY = new MtRegister();
        registerZ = new MtRegister();
        registerT = new MtRegister();
        decimalPointPressed = false;
        decimalPositions = 0;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonCLX() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonCLS();
            LOGGER.debug("end 1");
            return;
        }
        registerX = new MtRegister();
        forceRaiseStack = false;
        decimalPointPressed = false;
        decimalPositions = 0;

        LOGGER.debug("end");
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigitFixMode(long digit) {

        LOGGER.debug("begin");

        fixMode1 = false;
        fixModeSize = digit;
        fixModeLabel = "Fix " + Long.toString(digit);

        LOGGER.debug("end");
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigitExponent(long digit) {

        LOGGER.debug("begin");

        if (exponentPositions > 2) {
            LOGGER.debug("end 1");
            return;
        }
//        if (MtNumber.compare(registerX.getNumber(), MtNumber.ZERO) == 0) {
//            registerX = new MtRegister();
//            registerX.setNumber(MtNumber.ONE);
//        }
        if (exponentPositions == 1) {
            exponent.setNumber(new MtNumber(BigDecimal.valueOf(digit)));
            exponentPositions++;
            LOGGER.debug("end 2");
            return;
        }
        long exp = exponent.getNumber().getNumber().longValue();
        exp = exp * 10 + digit;
        exponent.setNumber(new MtNumber(BigDecimal.valueOf(exp)));
        exponentPositions++;

        LOGGER.debug("end");
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigitData(long digit) {

        LOGGER.debug("begin");

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
            LOGGER.debug("end 1");
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

        LOGGER.debug("end");
    }

    /**
     *
     * @param digit
     */
    private void pressButtonDigit(long digit) {

        LOGGER.debug("begin");

        if (fixMode1) {
            pressButtonDigitFixMode(digit);
        } else {
            forceRaiseStack = true;
            pressButtonAuto();
            pressButtonDigitData(digit);
        }

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton0() {

        LOGGER.debug("begin");

        pressButtonDigit(0L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton1() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonArcSin();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(1L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton2() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonArcCos();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(2L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton3() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonArcTan();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(3L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton4() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonSin();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(4L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton5() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonCos();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(5L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton6() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonTan();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(6L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton7() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonEPowX();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(7L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton8() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonLog();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(8L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButton9() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonTenPowerX();
            LOGGER.debug("end 1");
            return;
        }
        pressButtonDigit(9L);

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonPi() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonDecimalPoint() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonPi();
            LOGGER.debug("end 1");
            return;
        }
        if (decimalPointPressed) {
            LOGGER.debug("end 2");
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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonAdd() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonSin() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonArcSin() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonCos() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonArcCos() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonTan() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonArcTan() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonLn() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonEPowX() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonLog() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonTenPowerX() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonSubtract() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonLn();
            LOGGER.debug("end 1");
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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonMultiply() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonDivide() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonCHS() {

        LOGGER.debug("begin");

        if (exponentPressed) {
            MtRegister wrkExponent;
            wrkExponent = new MtRegister();
            wrkExponent.setNumber(MtNumber.multiply(exponent.getNumber(), new MtNumber(-1L)));
            exponent = wrkExponent;
            LOGGER.debug("end 1");
            return;
        }
//        reformatRegisterX();
        MtRegister register;
        register = new MtRegister();
        register.setNumber(MtNumber.multiply(registerX.getNumber(), new MtNumber(-1L)));
        registerLastX = registerX;
        registerX = register;
        forceRaiseStack = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void raiseStack() {

        LOGGER.debug("begin");

        reformatRegisterX();

        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerLastX = registerX;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonEnter() {

        LOGGER.debug("begin");
        if (indF) {
            indF = false;
            debugMode = !debugMode;
            LOGGER.debug("end 1");
            return;
        }
        decimalPointPressed = false;
        releaseButtonEEX();
        raiseStack();
        forceRaiseStack = false;
        eraseDisplay = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonDROP() {

        LOGGER.debug("begin");

        indF = false;
        // TODO Implement the logic here

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonRollDown() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonDROP();
            LOGGER.debug("end 1");
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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonDUP() {

        LOGGER.debug("begin");

        indF = false;
        // TODO Implement the logic here

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonRollUp() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonDUP();
            LOGGER.debug("end 1");
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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonCLS() {

        LOGGER.debug("begin");

        indF = false;
        registerS = new MtRegister();

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonYpowerX() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonSTO() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonYpowerX();
            LOGGER.debug("end 1");
            return;
        }
        reformatRegisterX();
        releaseButtonEEX();
        forceRaiseStack = false;
        registerS = registerX;
        eraseDisplay = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonXpowerY() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonRCL() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonXpowerY();
            LOGGER.debug("end 1");
            return;
        }
        registerLastX = registerX;
        raiseStack();
        registerX = registerS;
        eraseDisplay = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonSquareRoot() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonLastX() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonSquareRoot();
            LOGGER.debug("end 1");
            return;
        }
        registerT = registerZ;
        registerZ = registerY;
        registerY = registerX;
        registerX = registerLastX;
        eraseDisplay = true;
        forceRaiseStack = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonReciprocal() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    private void pressButtonSquare() {

        LOGGER.debug("begin");

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

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonSwapXandY() {

        LOGGER.debug("begin");

        if (indF) {
            pressButtonSquare();
            LOGGER.debug("end 1");
            return;
        }
        reformatRegisterX();
        registerLastX = registerX;
        MtRegister register;
        register = registerX;
        registerX = registerY;
        registerY = register;
        eraseDisplay = true;
        forceRaiseStack = true;

        LOGGER.debug("end");
    }

    /**
     * Set all display mode indicators to their defaults
     */
    private void clearMode() {

        LOGGER.debug("begin");

        autoMode = false;
        fixMode = false;
        fixMode1 = false;
        floatMode = false;
        engMode = false;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public static void pressButtonReset() {

        LOGGER.debug("begin");

        INSTANCE = null;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonAuto() {

        LOGGER.debug("begin");

        clearMode();
        autoMode = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonFix() {

        LOGGER.debug("begin");

        clearMode();
        fixMode = true;
        fixMode1 = true;
        fixModeSize = 0;
        fixModeLabel = "Fix ?";

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonFloat() {

        LOGGER.debug("begin");

        clearMode();
        floatMode = true;

        LOGGER.debug("end");
    }

    /**
     *
     */
    public void pressButtonEng() {

        LOGGER.debug("begin");

        clearMode();
        engMode = true;

        LOGGER.debug("end");
    }

    /**
     *
     * @return
     */
    private String getFixModeDecimalFormatString() {

        LOGGER.debug("begin");

        String decimalFormatString = "0000000000000";
        int decimalPointPosition;
        decimalPointPosition = (int) (decimalFormatString.length() - fixModeSize);
        decimalFormatString = decimalFormatString.substring(0, decimalPointPosition) + "." + decimalFormatString.substring(decimalPointPosition);
        for (int i = 1; i < decimalPointPosition; i++) {
            decimalFormatString = decimalFormatString.replaceFirst("0", "#");
        }

        LOGGER.debug("end");

        return decimalFormatString;
    }

    /**
     *
     * @return
     */
    private String getFloatModeDecimalFormatString() {

        LOGGER.debug("begin");

        String decimalFormatString;
        decimalFormatString = "0.000000000000E00";

        LOGGER.debug("end");

        return decimalFormatString;

    }

    /**
     *
     * @return
     */
    private String getEngModeDecimalFormatString() {

        LOGGER.debug("begin");

        String decimalFormatString;
        decimalFormatString = "##0.000000000000E00";

        LOGGER.debug("end");

        return decimalFormatString;
    }

    /**
     *
     * @param register
     * @return
     */
    private String registerFormatedStringAutoMode(MtRegister register) {

        LOGGER.debug("begin");

        String string;
        string = register.getNumber().getNumber().toPlainString();
        final int maxDigits;
        maxDigits = 16;
        if (string.length() > maxDigits) {
            string = string.substring(0, maxDigits);
        }

        LOGGER.debug("end");

        return string;
    }

    /**
     *
     * @param register
     * @return
     */
    private String registerFormatedString(MtRegister register) {

        LOGGER.debug("begin");

        if (register.getNumber().getNumber() == null) {
            LOGGER.debug("end1");
            return "Error";
        }

        String decimalFormatString;
        if (floatMode) {
            decimalFormatString = getFloatModeDecimalFormatString();
        } else if (engMode) {
            decimalFormatString = getEngModeDecimalFormatString();
        } else if (fixMode) {
            decimalFormatString = getFixModeDecimalFormatString();
        } else { // default autoMode
            LOGGER.debug("end 1");
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

        LOGGER.debug("end");

        return string;
    }

    /**
     *
     * @return
     */
    public String registerXFormatedString() {

        LOGGER.debug("begin");

        String registerXFormatedString2;
        registerXFormatedString2 = registerFormatedString(registerX);
        if (autoMode) {
            if (decimalPointPressed) {
                if (decimalPositions <= 1) {
                    registerXFormatedString2 = registerXFormatedString2.concat(".");
                }
            }
        }
        if (exponentPressed) {
            String registerXFormatedString3 = registerXFormatedString2.concat("               ");
            registerXFormatedString2 = registerXFormatedString3.substring(0, 12).concat(exponentFormatedString());
        }

        LOGGER.debug("end");

        return registerXFormatedString2;
    }

    /**
     *
     * @return
     */
    public String registerLastXFormatedString() {

        LOGGER.debug("begin");
        LOGGER.debug("end");

        return registerFormatedString(registerLastX);
    }

    /**
     *
     * @return
     */
    public String registerYFormatedString() {

        LOGGER.debug("begin");
        LOGGER.debug("end");

        return registerFormatedString(registerY);
    }

    /**
     *
     * @return
     */
    public String registerZFormatedString() {

        LOGGER.debug("begin");
        LOGGER.debug("end");

        return registerFormatedString(registerZ);
    }

    /**
     *
     * @return
     */
    public String registerTFormatedString() {

        LOGGER.debug("begin");
        LOGGER.debug("end");

        return registerFormatedString(registerT);
    }

    /**
     *
     * @return
     */
    public String registerSFormatedString() {

        LOGGER.debug("begin");
        LOGGER.debug("end");

        return registerFormatedString(registerS);
    }

    /**
     *
     * @return
     */
    public String exponentFormatedString() {

        LOGGER.debug("begin");

        String decimalFormatString = "#00";

        DecimalFormat decimalFormat;
        decimalFormat = new DecimalFormat(decimalFormatString);
        String string;
        string = decimalFormat.format(exponent.getNumber().getNumber());

        if (string.length() < 3) {
            string = " " + string;
        }

        LOGGER.debug("end");

        return string;
    }

    /**
     *
     */
    private void reformatRegisterX() {

        LOGGER.debug("begin");

        registerX.getNumber().stripTrailingZeroes();

        LOGGER.debug("end");
    }
}
