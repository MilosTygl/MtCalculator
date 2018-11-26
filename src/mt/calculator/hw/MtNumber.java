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
    public static final MtNumber PI = new MtNumber(new BigDecimal(Math.PI));
    public static final MtNumber E = new MtNumber(new BigDecimal(Math.E));

    /**
     *
     */
    MtNumber() {
        number = BigDecimal.ZERO;
    }

    /**
     *
     * @param bigDecimal
     */
    MtNumber(BigDecimal bigDecimal) {
        number = bigDecimal;
    }

    /**
     *
     * @param l
     */
    MtNumber(long l) {
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
        x = new BigDecimal(Math.sqrt(n.getNumber().doubleValue()));
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
        wrkNumber = new MtNumber(new BigDecimal(x));
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
        wrkNumber = new MtNumber(new BigDecimal(x));
        return wrkNumber;
    }

    /**
     *
     * @param n
     * @return
     */
    public static MtNumber ePowX(MtNumber n) {
        double x;
        x = Math.pow(MtNumber.E.getNumber().doubleValue(), n.getNumber().doubleValue());
        MtNumber wrkNumber;
        wrkNumber = new MtNumber(new BigDecimal(x));
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
        wrkNumber = new MtNumber(new BigDecimal(x));
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
