package by.htp.hvozdzeu.util;

/**
 * Class for hide symbols credit card
 */
public class HideSymbolsCreditCard {

    /**
     * Constant with replacement characters
     */
    private static final String HIDE_SYMBOL = " **** **** ";

    /**
     * Private constructor
     */
    private HideSymbolsCreditCard() {
    }

    /**
     * Method for hide symbols in credit card number.
     * Split the credit card number by spaces and
     * replace it with symbols from the constant.
     * @param creditCardNumber String Card number before hiding symbols
     * @return String Card number after hiding symbols
     */
    public static String hideSymbolsCreditCard(String creditCardNumber){
        String firstPart = creditCardNumber.split(" ")[0];
        String secondPart = creditCardNumber.split(" ")[3];
        return firstPart + HIDE_SYMBOL + secondPart;
    }

}
