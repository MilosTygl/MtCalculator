package mt.calculator.hw;

import org.apache.log4j.Logger;

public class MtRegister {

    private static final Logger LOGGER = Logger.getLogger(MtRegister.class);

    private MtNumber number;
    private String editableString;

    /**
     * 
     */
    public MtRegister() {
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
