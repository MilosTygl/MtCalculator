package mt.calculator.hw;

import mt.calculator.consts.Consts;

import org.apache.log4j.Logger;

/**
 *
 * @author milos.tygl
 */
public class MtRegister {

    private static final Logger LOGGER = Logger.getLogger(MtRegister.class);

    private static final String LOG_BEGIN = Consts.getLogBegin();
    private static final String LOG_END = Consts.getLogEnd();
    private static final String LOG_EXCEPTION = Consts.getLogException();

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
