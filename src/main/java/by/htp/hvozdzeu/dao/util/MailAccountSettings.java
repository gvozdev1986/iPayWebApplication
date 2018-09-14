package by.htp.hvozdzeu.dao.util;

public class MailAccountSettings {

    private static final String SQL_CREATE = "INSERT "
            + "INTO `ipaywebapplication`.`bankaccount` "
            + "(" +
            "`CreditCard`, " +
            "`NameAccount`, " +
            "`StatusBankAccount`, " +
            "`BalanceBankAccount`, " +
            "`Available`) VALUES (?, ?, ?, ?, ?);";

}
