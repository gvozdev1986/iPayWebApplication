package by.htp.hvozdzeu.web.command.impl.card.helper;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.PaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * The class for save transaction information in service
 */
public class SavePaymentInformation {

    private static PaymentService paymentService = ServiceFactory.getPaymentService();
    private static CreditCardService creditCardService = ServiceFactory.getCreditCardService();

    /**
     * Private constructor
     */
    private SavePaymentInformation() {
    }

    /**
     * The method for save information about write-off money from bank account
     *
     * @param amount               BigDecimal amount write-off
     * @param creditCardNumber     String credit card with which wrote-off money
     * @param paymentDataServiceId Long ID payment service for identification
     * @throws DAOException Exception
     */
    public static void writeOffBalance(BigDecimal amount, String creditCardNumber, Long paymentDataServiceId) throws DAOException {

        CreditCard creditCard = creditCardService.findByCreditCardNumber(creditCardNumber);

        String writeOffDescription = "Transaction write-off "
                + amount + " point from bank account. Credit card [" + creditCard.getCardNumber() + "]";

        Payment paymentWriteOff = Payment.getBuilder()
                .datePayment(new Date(System.currentTimeMillis()).toLocalDate())
                .timePayment(new Time(System.currentTimeMillis()).toLocalTime())
                .descriptionPayment(writeOffDescription)
                .paymentService(paymentDataServiceId).amountPayment(amount)
                .creditCard(creditCard.getId()).build();
        paymentService.create(paymentWriteOff);
    }

    /**
     * The method for save information about refill (back) money to bank account
     *
     * @param amount               BigDecimal amount refill (back)
     * @param creditCardNumber     String credit card with which refilled (backed) money
     * @param paymentDataServiceId Long ID payment service for identification
     * @throws DAOException Exception
     */
    public static void refillBalance(BigDecimal amount, String creditCardNumber, Long paymentDataServiceId) throws DAOException {

        CreditCard creditCard = creditCardService.findByCreditCardNumber(creditCardNumber);

        String refillDescription = "Transaction refill "
                + amount + " point to bank account. Credit card [" + creditCard.getCardNumber() + "]";

        Payment paymentReFill = Payment.getBuilder()
                .datePayment(new Date(System.currentTimeMillis()).toLocalDate())
                .timePayment(new Time(System.currentTimeMillis()).toLocalTime())
                .descriptionPayment(refillDescription)
                .paymentService(paymentDataServiceId).amountPayment(amount)
                .creditCard(creditCard.getId()).build();
        paymentService.create(paymentReFill);
    }

}
