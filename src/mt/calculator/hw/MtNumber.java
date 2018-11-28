package mt.calculator.hw;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.log4j.Logger;

public class MtNumber {

    private static final Logger LOGGER = Logger.getLogger(MtNumber.class);

    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

    BigDecimal number;

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
     * @param n1
     * @param n2
     * @return
     */
    public static MtNumber add(MtNumber n1, MtNumber n2) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        wrkNumber.setNumber(n1.getNumber().add(n2.getNumber(), MATH_CONTEXT));
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
        wrkNumber.setNumber(n1.getNumber().subtract(n2.getNumber(), MATH_CONTEXT));
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
        wrkNumber.setNumber(n1.getNumber().multiply(n2.getNumber(), MATH_CONTEXT));
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
        wrkNumber.setNumber(n1.getNumber().divide(n2.getNumber(), MATH_CONTEXT));
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber sqrRoot(MtNumber n) {
        MtNumber wrkNumber;
        wrkNumber = new MtNumber();
        BigDecimal x;
        x = BigDecimal.valueOf(Math.sqrt(n.getNumber().doubleValue()));
        wrkNumber.setNumber(x);
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber ln(MtNumber n) {
        double x;
        x = Math.log(n.getNumber().doubleValue());
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(BigDecimal.valueOf(x));
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber log(MtNumber n) {
        double x;
        x = Math.log10(n.getNumber().doubleValue());
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(BigDecimal.valueOf(x));
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber ePowX(MtNumber n) {
        double x;
        x = Math.pow(Math.E, n.getNumber().doubleValue());
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(BigDecimal.valueOf(x));
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber tenPowX(MtNumber n) {
        double x;
        x = Math.pow(MtNumber.TEN.getNumber().doubleValue(), n.getNumber().doubleValue());
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(BigDecimal.valueOf(x));
        return wrkNumber;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static MtNumber pow(MtNumber n1, MtNumber n2) {
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
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(bd);
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
        BigDecimal bd;
        bd = n.getNumber();
        bd = bd.multiply(MtNumber.PI.getNumber(), MathContext.DECIMAL128);
        bd = bd.divide(BigDecimal.valueOf(180L), MathContext.DECIMAL128);
        wrkNumber.setNumber(bd);
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber sin(MtNumber n) {
        double x;
        x = Math.sin(n.getNumber().doubleValue());
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(BigDecimal.valueOf(x));
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber cos(MtNumber n) {
        double x;
        x = Math.cos(n.getNumber().doubleValue());
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(BigDecimal.valueOf(x));
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber tan(MtNumber n) {
        double x;
        x = Math.tan(n.getNumber().doubleValue());
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(BigDecimal.valueOf(x));
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

}
