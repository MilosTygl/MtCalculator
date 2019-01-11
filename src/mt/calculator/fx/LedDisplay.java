package mt.calculator.fx;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class LedDisplay {

    private static final Logger LOGGER = Logger.getLogger(LedDisplay.class);

    private String displayString;
    private int displayStringIndex;

    /**
     *
     * @return
     */
    private char nextDigitFromDisplayString() {

        final char space;
        space = ' ';

        displayStringIndex++;
        if (displayStringIndex < 0) {
            return space;
        }
        if (displayString == null) {
            return space;
        }
        if (displayString.length() == 0) {
            return space;
        }
        if (displayString.length() < displayStringIndex + 1) {
            return space;
        }
        char displayDigit;
        displayDigit = displayString.charAt(displayStringIndex);

        return displayDigit;
    }

    /**
     *
     * @param ledDisplayString
     * @param ledDisplayPane
     */
    public void displayNumberOnLedDisplay(String ledDisplayString, Pane ledDisplayPane) {

        LOGGER.debug("begin");

        displayString = ledDisplayString;
        displayStringIndex = -1;

        ObservableList observableListDisplayPane;
        observableListDisplayPane = ledDisplayPane.getChildren();

        Object[] objectDisplayPane;
        objectDisplayPane = observableListDisplayPane.toArray();

        for (Object objectDigitPane : objectDisplayPane) {
            Pane digitPane;
            digitPane = (Pane) objectDigitPane;

            ObservableList observableListDigitPane;
            observableListDigitPane = digitPane.getChildren();

            Object[] objectDigitSegment;
            objectDigitSegment = observableListDigitPane.toArray();

            char cx;
            cx = nextDigitFromDisplayString();
            LedDigit ledDigit;
            ledDigit = new LedDigit(cx);

            Shape shapeDigitSegmentA;
            shapeDigitSegmentA = (Shape) objectDigitSegment[0];
            shapeDigitSegmentA.setVisible(ledDigit.isSegmentA());

            Shape shapeDigitSegmentB;
            shapeDigitSegmentB = (Shape) objectDigitSegment[1];
            shapeDigitSegmentB.setVisible(ledDigit.isSegmentB());

            Shape shapeDigitSegmentC;
            shapeDigitSegmentC = (Shape) objectDigitSegment[2];
            shapeDigitSegmentC.setVisible(ledDigit.isSegmentC());

            Shape shapeDigitSegmentD;
            shapeDigitSegmentD = (Shape) objectDigitSegment[3];
            shapeDigitSegmentD.setVisible(ledDigit.isSegmentD());

            Shape shapeDigitSegmentE;
            shapeDigitSegmentE = (Shape) objectDigitSegment[4];
            shapeDigitSegmentE.setVisible(ledDigit.isSegmentE());

            Shape shapeDigitSegmentF;
            shapeDigitSegmentF = (Shape) objectDigitSegment[5];
            shapeDigitSegmentF.setVisible(ledDigit.isSegmentF());

            Shape shapeDigitSegmentG;
            shapeDigitSegmentG = (Shape) objectDigitSegment[6];
            shapeDigitSegmentG.setVisible(ledDigit.isSegmentG());

            Shape shapeDigitSegmentP;
            shapeDigitSegmentP = (Shape) objectDigitSegment[7];
            shapeDigitSegmentP.setVisible(ledDigit.isSegmentP());
        }
        LOGGER.debug("end");
    }
}
