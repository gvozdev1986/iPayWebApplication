package by.htp.hvozdzeu.service.factory;

import by.htp.hvozdzeu.service.*;
import by.htp.hvozdzeu.service.impl.*;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static IClientService getClientService() {
        return new ClientServiceImpl();
    }

    public static IBankAccountService getBankAccountService() {
        return new BankAccountServiceImpl();
    }

    public static ICreditCardService getCreditCardService() {
        return new CreditCardServiceImpl();
    }

    public static IPaymentService getPaymentService() {
        return new PaymentServiceImpl();
    }

    public static IPaymentDataService getPaymentDataService() {
        return new PaymentDataServiceImpl();
    }

    public static IMessageContactService getMessageContactService() {
        return new MessageContactServiceImpl();
    }

}
