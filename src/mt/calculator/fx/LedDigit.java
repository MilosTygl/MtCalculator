package mt.calculator.fx;

import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class LedDigit {

    private static final Logger LOGGER = Logger.getLogger(LedDigit.class);

    private boolean segmentA;
    private boolean segmentB;
    private boolean segmentC;
    private boolean segmentD;
    private boolean segmentE;
    private boolean segmentF;
    private boolean segmentG;
    private boolean segmentP;

    /**
     *
     * @param c
     */
    public LedDigit(char c) {
        displayDigitNone();
        switch (c) {
            case ' ':
                displaySpace();
                break;
            case '-':
                displayMinus();
                break;
            case '.':
                displayDecimalPoint();
                break;
            case '0':
                displayDigit0();
                break;
            case '1':
                displayDigit1();
                break;
            case '2':
                displayDigit2();
                break;
            case '3':
                displayDigit3();
                break;
            case '4':
                displayDigit4();
                break;
            case '5':
                displayDigit5();
                break;
            case '6':
                displayDigit6();
                break;
            case '7':
                displayDigit7();
                break;
            case '8':
                displayDigit8();
                break;
            case '9':
                displayDigit9();
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    private void displayDigitNone() {
        segmentA = false;
        segmentB = false;
        segmentC = false;
        segmentD = false;
        segmentE = false;
        segmentF = false;
        segmentG = false;
        segmentP = false;
    }

    /**
     *
     */
    private void displaySpace() {
    }

    /**
     *
     */
    private void displayMinus() {
        segmentG = true;
    }

    /**
     *
     */
    private void displayDigit0() {
        segmentA = true;
        segmentB = true;
        segmentC = true;
        segmentD = true;
        segmentE = true;
        segmentF = true;
    }

    /**
     *
     */
    private void displayDigit1() {
        segmentB = true;
        segmentC = true;
    }

    /**
     *
     */
    private void displayDigit2() {
        segmentA = true;
        segmentB = true;
        segmentG = true;
        segmentE = true;
        segmentD = true;
    }

    /**
     *
     */
    private void displayDigit3() {
        segmentA = true;
        segmentB = true;
        segmentC = true;
        segmentD = true;
        segmentG = true;
    }

    /**
     *
     */
    private void displayDigit4() {
        segmentB = true;
        segmentC = true;
        segmentF = true;
        segmentG = true;
    }

    /**
     *
     */
    private void displayDigit5() {
        segmentA = true;
        segmentC = true;
        segmentD = true;
        segmentF = true;
        segmentG = true;
    }

    /**
     *
     */
    private void displayDigit6() {
        segmentA = true;
        segmentC = true;
        segmentD = true;
        segmentE = true;
        segmentF = true;
        segmentG = true;
    }

    /**
     *
     */
    private void displayDigit7() {
        segmentA = true;
        segmentB = true;
        segmentC = true;
    }

    /**
     *
     */
    private void displayDigit8() {
        segmentA = true;
        segmentB = true;
        segmentC = true;
        segmentD = true;
        segmentE = true;
        segmentF = true;
        segmentG = true;
    }

    /**
     *
     */
    private void displayDigit9() {
        segmentA = true;
        segmentB = true;
        segmentC = true;
        segmentD = true;
        segmentF = true;
        segmentG = true;
    }

    /**
     *
     */
    private void displayDecimalPoint() {
        segmentP = true;
    }

    public boolean isSegmentA() {
        return segmentA;
    }

    public boolean isSegmentB() {
        return segmentB;
    }

    public boolean isSegmentC() {
        return segmentC;
    }

    public boolean isSegmentD() {
        return segmentD;
    }

    public boolean isSegmentE() {
        return segmentE;
    }

    public boolean isSegmentF() {
        return segmentF;
    }

    public boolean isSegmentG() {
        return segmentG;
    }

    public boolean isSegmentP() {
        return segmentP;
    }
}
