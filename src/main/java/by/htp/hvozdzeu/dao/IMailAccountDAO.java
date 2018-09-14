package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MailAccount;

public interface IMailAccountDAO {

    ConnectionPool dataBaseConnection = ConnectionPool.getInstance();

    void update(String mailLogin, String mailPswd) throws DAOException;
    MailAccount read() throws DAOException;

}
