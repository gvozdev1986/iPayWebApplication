package by.htp.hvozdzeu.service.factory;

import by.htp.hvozdzeu.service.*;
import by.htp.hvozdzeu.service.impl.*;

/**
 * Class that provides instances of service
 */
public class ServiceFactory {

    private static UserService userService;
    private static BankAccountService bankAccountService;
    private static CreditCardService creditCardService;
    private static PaymentService paymentService;
    private static PaymentDataService paymentDataService;
    private static MessageContactService messageContactService;
    private static MailAccountService mailAccountService;

    static{
        userService = new UserServiceImpl();
        bankAccountService = new BankAccountServiceImpl();
        creditCardService = new CreditCardServiceImpl();
        paymentService = new PaymentServiceImpl();
        paymentDataService = new PaymentDataServiceImpl();
        messageContactService = new MessageContactServiceImpl();
        mailAccountService = new MailAccountServiceImpl();
    }

    private ServiceFactory() {
    }

    public static UserService getUserService() {
        return userService;
    }

    public static BankAccountService getBankAccountService() {
        return bankAccountService;
    }

    public static CreditCardService getCreditCardService() {
        return creditCardService;
    }

    public static PaymentService getPaymentService() {
        return paymentService;
    }

    public static PaymentDataService getPaymentDataService() {
        return paymentDataService;
    }

    public static MessageContactService getMessageContactService() {
        return messageContactService;
    }

    public static MailAccountService getMailAccountService() {
        return mailAccountService;
    }

}
