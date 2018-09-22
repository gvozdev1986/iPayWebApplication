package by.htp.hvozdzeu.util;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class HideSymbolsCreditCardTest {

    @Test
    public void testHideSymbolsInCreditCard_returnNewCreditCardNumber() {
        String cardNumber = "0000 0000 0000 0000";
        String firstPart = cardNumber.split(" ")[0];
        String secondPart = cardNumber.split(" ")[3];
        String hideSymbol = " **** **** ";
        String newCardNumber = firstPart + hideSymbol + secondPart;
        assertNotEquals(cardNumber, newCardNumber);
    }

}