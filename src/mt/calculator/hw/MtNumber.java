package mt.calculator.hw;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class MtNumber {

    private static final Logger LOGGER = Logger.getLogger(MtNumber.class);

    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

    private BigDecimal number;

    public static final MtNumber ZERO = new MtNumber(BigDecimal.ZERO);
    public static final MtNumber ONE = new MtNumber(BigDecimal.ONE);
    public static final MtNumber TEN = new MtNumber(BigDecimal.TEN);
    public static final MtNumber PI = new MtNumber(BigDecimal.valueOf(Math.PI));
    public static final MtNumber E = new MtNumber(BigDecimal.valueOf(Math.E));

    /**
     *
     */
    MtNumber() {
        LOGGER.debug("MtNumber()");
        number = BigDecimal.ZERO;
    }

    /**
     *
     * @param bigDecimal
     */
    MtNumber(BigDecimal bigDecimal) {
        LOGGER.debug("MtNumber(BigDecimal bigDecimal)");
        number = bigDecimal;
    }

    /**
     *
     * @param l
     */
    MtNumber(long l) {
        LOGGER.debug("MtNumber(long l)");
        number = new BigDecimal(l);
    }

    /**
     *
     * @param string
     */
    MtNumber(String string) {
        LOGGER.debug("MtNumber(String string)");
        number = new BigDecimal(string);
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static MtNumber add(MtNumber n1, MtNumber n2) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            wrkNumber.setNumber(n1.getNumber().add(n2.getNumber(), MATH_CONTEXT));
//            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static MtNumber subtract(MtNumber n1, MtNumber n2) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            wrkNumber.setNumber(n1.getNumber().subtract(n2.getNumber(), MATH_CONTEXT));
//            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static MtNumber multiply(MtNumber n1, MtNumber n2) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            wrkNumber.setNumber(n1.getNumber().multiply(n2.getNumber(), MATH_CONTEXT));
//            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static MtNumber divide(MtNumber n1, MtNumber n2) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            wrkNumber.setNumber(n1.getNumber().divide(n2.getNumber(), MATH_CONTEXT));
//            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber squareRoot(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        BigDecimal x;
        try {
            x = BigDecimal.valueOf(Math.sqrt(n.getNumber().doubleValue()));
            wrkNumber.setNumber(x);
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber ln(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.log(n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber log(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.log10(n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber ePowX(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.pow(Math.E, n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber tenPowX(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.pow(MtNumber.TEN.getNumber().doubleValue(), n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static MtNumber pow(MtNumber n1, MtNumber n2) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double d1;
            d1 = n1.getNumber().doubleValue();
            double d2;
            d2 = n2.getNumber().doubleValue();
            double dw;
            dw = Math.log(d1);
            dw = dw * d2;
            dw = Math.pow(Math.E, dw);
            BigDecimal bd;
            bd = BigDecimal.valueOf(dw);
            wrkNumber = new MtNumber(bd);
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber degToRad(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        if (n.getNumber() == null) {
            wrkNumber.setNumber(null);
            return wrkNumber;
        }
        try {
            BigDecimal bd;
            bd = n.getNumber();
            bd = bd.multiply(MtNumber.PI.getNumber(), MathContext.DECIMAL128);
            bd = bd.divide(BigDecimal.valueOf(180L), MathContext.DECIMAL128);
            wrkNumber.setNumber(bd);
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber radToDeg(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        if (n.getNumber() == null) {
            wrkNumber.setNumber(null);
            return wrkNumber;
        }
        try {
            BigDecimal bd;
            bd = n.getNumber();
            bd = bd.multiply(BigDecimal.valueOf(180L), MathContext.DECIMAL128);
            bd = bd.divide(MtNumber.PI.getNumber(), MathContext.DECIMAL128);
            wrkNumber.setNumber(bd);
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber sin(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.sin(n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber arcSin(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.asin(n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber cos(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.cos(n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber arcCos(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.acos(n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber tan(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.tan(n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber arcTan(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        try {
            double x;
            x = Math.atan(n.getNumber().doubleValue());
            wrkNumber = new MtNumber(BigDecimal.valueOf(x));
            wrkNumber.stripTrailingZeroes();
        } catch (ArithmeticException | NumberFormatException ae) {
            wrkNumber.setNumber(null);
        }
        return wrkNumber;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static int compare(MtNumber n1, MtNumber n2) {
        return n1.getNumber().compareTo(n2.getNumber());
    }

    /**
     *
     * @return
     */
    public BigDecimal getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    /**
     *
     * @return
     */
    public String toPlainString() {
        return number.toPlainString();
    }

    /**
     *
     */
    public void stripTrailingZeroes() {
        number = number.stripTrailingZeros();
    }
}
