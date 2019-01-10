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
        displayStringIndex++;
        if (displayStringIndex < 0) {
            return ' ';
        }
        if (displayString == null) {
            return ' ';
        }
        if (displayString.length() == 0) {
            return ' ';
        }
        if (displayString.length() < displayStringIndex + 1) {
            return ' ';
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

            Shape shapeDigitSegmentA;
            shapeDigitSegmentA = (Shape) objectDigitSegment[0];
            Shape shapeDigitSegmentB;
            shapeDigitSegmentB = (Shape) objectDigitSegment[1];
            Shape shapeDigitSegmentC;
            shapeDigitSegmentC = (Shape) objectDigitSegment[2];
            Shape shapeDigitSegmentD;
            shapeDigitSegmentD = (Shape) objectDigitSegment[3];
            Shape shapeDigitSegmentE;
            shapeDigitSegmentE = (Shape) objectDigitSegment[4];
            Shape shapeDigitSegmentF;
            shapeDigitSegmentF = (Shape) objectDigitSegment[5];
            Shape shapeDigitSegmentG;
            shapeDigitSegmentG = (Shape) objectDigitSegment[6];
            Shape shapeDigitSegmentP;
            shapeDigitSegmentP = (Shape) objectDigitSegment[7];

            char cx;
            cx = nextDigitFromDisplayString();
            LedDigit ledDigit;
            ledDigit = new LedDigit(cx);

            shapeDigitSegmentA.setVisible(ledDigit.isSegmentA());
            shapeDigitSegmentB.setVisible(ledDigit.isSegmentB());
            shapeDigitSegmentC.setVisible(ledDigit.isSegmentC());
            shapeDigitSegmentD.setVisible(ledDigit.isSegmentD());
            shapeDigitSegmentE.setVisible(ledDigit.isSegmentE());
            shapeDigitSegmentF.setVisible(ledDigit.isSegmentF());
            shapeDigitSegmentG.setVisible(ledDigit.isSegmentG());
            shapeDigitSegmentP.setVisible(ledDigit.isSegmentP());
        }
        LOGGER.debug("end");
    }
}
