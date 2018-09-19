package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.IMailAccountDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.MailAccountRowMapper;
import by.htp.hvozdzeu.model.MailAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class MailAccountCrudImpl extends MailAccountRowMapper implements IMailAccountDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailAccountCrudImpl.class);

    private static final String SQL_UPDATE_MAIL_ACCOUNT_DATA = "UPDATE `ipaywebapplication`.`mailsettings` SET " +
            "`MailLogin`= ?, " +
            "`MailPswd`= ? " +
            "WHERE  `Id`= 1;";

    private static final String SQL_READ_MAIL_ACCOUNT_DATA = "SELECT `Id`, `MailLogin`, `MailPswd` FROM " +
            "`ipaywebapplication`.`mailsettings` " +
            "WHERE  `Id`= ?;";

    @Override
    public void update(String mailLogin, String mailPswd) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_MAIL_ACCOUNT_DATA)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, mailLogin);
            preparedStatement.setString(2, mailPswd);
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }

    @Override
    public MailAccount read() throws DAOException {
        MailAccount mailAccount = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_MAIL_ACCOUNT_DATA)) {
            preparedStatement.setLong(1, 1L);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    mailAccount = buildMailAccountRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return mailAccount;
    }

}
