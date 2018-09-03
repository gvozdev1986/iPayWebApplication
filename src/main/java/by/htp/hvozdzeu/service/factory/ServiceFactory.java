package by.htp.hvozdzeu.service.factory;

import by.htp.hvozdzeu.service.*;
import by.htp.hvozdzeu.service.impl.*;

public class ServiceFactory {

    private static IUserService iUserService;
    private static IBankAccountService iBankAccountService;
    private static ICreditCardService iCreditCardService;
    private static IPaymentService iPaymentService;
    private static IPaymentDataService iPaymentDataService;
    private static IMessageContactService iMessageContactService;

    static{
        iUserService = new UserServiceImpl();
        iBankAccountService = new BankAccountServiceImpl();
        iCreditCardService = new CreditCardServiceImpl();
        iPaymentService = new PaymentServiceImpl();
        iPaymentDataService = new PaymentDataServiceImpl();
        iMessageContactService = new MessageContactServiceImpl();
    }

    private ServiceFactory() {
    }

    public static IUserService getUserService() {
        return iUserService;
    }

    public static IBankAccountService getBankAccountService() {
        return iBankAccountService;
    }

    public static ICreditCardService getCreditCardService() {
        return iCreditCardService;
    }

    public static IPaymentService getPaymentService() {
        return iPaymentService;
    }

    public static IPaymentDataService getPaymentDataService() {
        return iPaymentDataService;
    }

    public static IMessageContactService getMessageContactService() {
        return iMessageContactService;
    }

}
