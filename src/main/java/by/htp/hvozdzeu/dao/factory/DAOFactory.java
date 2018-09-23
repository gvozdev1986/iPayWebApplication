package by.htp.hvozdzeu.dao.factory;

import by.htp.hvozdzeu.dao.*;
import by.htp.hvozdzeu.dao.impl.*;

/**
 * Class that provides instances of DAO
 */
public class DAOFactory {

    private static UserDAO userDAO;
    private static BankAccountDAO bankAccountDAO;
    private static CreditCardDAO creditCardDAO;
    private static PaymentDAO paymentDAO;
    private static PaymentDataDAO paymentDataDAO;
    private static MessageContactDAO messageContactDAO;
    private static MailAccountDAO mailAccountDAO;

    static {
        userDAO = new UserCrudImpl();
        bankAccountDAO = new BankAccountCrudImpl();
        creditCardDAO = new CreditCardCrudImpl();
        paymentDAO = new PaymentCrudImpl();
        paymentDataDAO = new PaymentDataCrudImpl();
        messageContactDAO = new MessageContactCrudImpl();
        mailAccountDAO = new MailAccountCrudImpl();
    }


    private DAOFactory() {
    }

    public static UserDAO getClientDao() {
        return userDAO;
    }

    public static BankAccountDAO getBankAccountDao() {
        return bankAccountDAO;
    }

    public static CreditCardDAO getCreditCardDao() {
        return creditCardDAO;
    }

    public static PaymentDAO getPaymentDao() {
        return paymentDAO;
    }

    public static PaymentDataDAO getPaymentServiceDao() {
        return paymentDataDAO;
    }

    public static MessageContactDAO getMessageContactDAO() {
        return messageContactDAO;
    }

    public static MailAccountDAO getMailAccountDAO() {
        return mailAccountDAO;
    }

}
