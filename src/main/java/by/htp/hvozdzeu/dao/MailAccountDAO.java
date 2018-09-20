package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MailAccount;

/**
 * Interface for MailAccount entity
 */
public interface MailAccountDAO {

    /**
     * Instance Connection pool for use in implementation
     */
    ConnectionPool dataBaseConnection = ConnectionPool.getInstance();

    /**
     * Update account data method for use mail account
     * @param mailLogin String
     * @param mailPswd String
     * @throws DAOException Custom exception
     */
    void update(String mailLogin, String mailPswd) throws DAOException;

    /**
     * Read account data method for use mail account by ID 1L
     * as it's one row in this table
     * @return MailAccount entity
     * @throws DAOException Custom exception
     */
    MailAccount read() throws DAOException;

}
