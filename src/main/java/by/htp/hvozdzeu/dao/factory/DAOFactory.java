package by.htp.hvozdzeu.dao.factory;

import by.htp.hvozdzeu.dao.*;
import by.htp.hvozdzeu.dao.impl.*;

public class DAOFactory {

    private DAOFactory() {
    }

    public static IBankAccountDAO getBankAccountDao() {
        return new BankAccountCrudImpl();
    }

    public static IClientDAO getClientDao() {
        return new ClientCrudImpl();
    }

    public static ICreditCardDAO getCreditCardDao() {
        return new CreditCardCrudImpl();
    }

    public static IPaymentDAO getPaymentDao() {
        return new PaymentCrudImpl();
    }

    public static IPaymentDataDAO getPaymentServiceDao() {
        return new PaymentDataCrudImpl();
    }

    public static IMessageContactDAO getMessageContactDAO() {
        return new MessageContactCrudImpl();
    }

}
