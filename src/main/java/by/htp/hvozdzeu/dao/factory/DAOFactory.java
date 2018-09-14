package by.htp.hvozdzeu.dao.factory;

import by.htp.hvozdzeu.dao.*;
import by.htp.hvozdzeu.dao.impl.*;

public class DAOFactory {

    private static IUserDAO iUserDAO;
    private static IBankAccountDAO iBankAccountDAO;
    private static ICreditCardDAO iCreditCardDAO;
    private static IPaymentDAO iPaymentDAO;
    private static IPaymentDataDAO iPaymentDataDAO;
    private static IMessageContactDAO iMessageContactDAO;
    private static IMailAccountDAO iMailAccountDAO;

    static {
        iUserDAO = new UserCrudImpl();
        iBankAccountDAO = new BankAccountCrudImpl();
        iCreditCardDAO = new CreditCardCrudImpl();
        iPaymentDAO = new PaymentCrudImpl();
        iPaymentDataDAO = new PaymentDataCrudImpl();
        iMessageContactDAO = new MessageContactCrudImpl();
        iMailAccountDAO = new MailAccountCrudImpl();
    }


    private DAOFactory() {
    }

    public static IUserDAO getClientDao() {
        return iUserDAO;
    }

    public static IBankAccountDAO getBankAccountDao() {
        return iBankAccountDAO;
    }

    public static ICreditCardDAO getCreditCardDao() {
        return iCreditCardDAO;
    }

    public static IPaymentDAO getPaymentDao() {
        return iPaymentDAO;
    }

    public static IPaymentDataDAO getPaymentServiceDao() {
        return iPaymentDataDAO;
    }

    public static IMessageContactDAO getMessageContactDAO() {
        return iMessageContactDAO;
    }

    public static IMailAccountDAO getiMailAccountDAO() {
        return iMailAccountDAO;
    }

}
