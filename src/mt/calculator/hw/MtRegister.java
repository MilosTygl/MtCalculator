package mt.calculator.hw;

import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class MtRegister {

    private static final Logger LOGGER = Logger.getLogger(MtRegister.class);

    private MtNumber number;

    /**
     *
     */
    public MtRegister() {

        LOGGER.debug("MtRegister()");

        this.number = new MtNumber();
    }

    /**
     *
     * @return
     */
    public MtNumber getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(MtNumber number) {
        this.number = number;
    }
}
