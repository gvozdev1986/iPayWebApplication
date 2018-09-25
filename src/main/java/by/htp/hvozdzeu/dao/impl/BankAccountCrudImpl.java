package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.BankAccountDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.BankAccountRowMapper;
import by.htp.hvozdzeu.model.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountCrudImpl extends BankAccountRowMapper implements BankAccountDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountCrudImpl.class);

    private static final String SQL_CREATE = "INSERT "
            + "INTO `ipaywebapplication`.`bankaccount` "
            + "(`CreditCard`, "
            + "`NameAccount`, "
            + "`StatusBankAccount`, "
            + "`BalanceBankAccount`, "
            + "`Available`) VALUES (?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_BALANCE_BY_ACCOUNT_ID = "UPDATE "
            + "`ipaywebapplication`.`bankaccount` "
            + "SET `BalanceBankAccount`=? WHERE  `Id`=?;";

    private static final String SQL_FIND_BY_ID = "SELECT "
            + "`Id`, `NameAccount`, "
            + "`CreditCard`, "
            + "`StatusBankAccount`, "
            + "`BalanceBankAccount`, "
            + "`Available` "
            + "FROM `ipaywebapplication`.`bankaccount` "
            + "WHERE `Id` = ?;";

    private static final String SQL_READ = "SELECT " +
            "`Id`, "
            + "`NameAccount`, "
            + "`CreditCard`, "
            + "`StatusBankAccount`, "
            + "`BalanceBankAccount`, "
            + "`Available` "
            + "FROM `ipaywebapplication`.`bankaccount`;";

    private static final String SQL_DELETE_BY_ID = "UPDATE "
            + "`ipaywebapplication`.`bankaccount` "
            + "SET `Available` = 0 WHERE `Id` = ?;";

    private static final String SQL_BLOCK_ACCOUNT_BY_ID = "UPDATE "
            + "`ipaywebapplication`.`bankaccount` "
            + "SET `StatusBankAccount`='0' WHERE  `Id`= ?;";

    private static final String SQL_UNBLOCK_ACCOUNT_BY_ID = "UPDATE "
            + "`ipaywebapplication`.`bankaccount` "
            + "SET `StatusBankAccount`='1' WHERE  `Id`= ?;";

    private static final String SQL_FIND_ACCOUNT_BY_CARD_ID = "SELECT "
            + "`Id`, "
            + "`NameAccount`, "
            + "`CreditCard`, "
            + "`StatusBankAccount`, "
            + "`BalanceBankAccount`, "
            + "`Available` "
            + "FROM `ipaywebapplication`.`bankaccount` WHERE `CreditCard` = ?;";

    private static final String ERROR_CREATE = "Error save bank account.";
    private static final String ERROR_FIND_BY_ID = "Error find bank account by id.";
    private static final String ERROR_READ = "Error getAllUsers bank accounts.";
    private static final String ERROR_DELETE_BY_ID = "Error delete bank account by id.";
    private static final String ERROR_BLOCK_ACCOUNT_BY_ID = "Error block bank account by id.";
    private static final String ERROR_UNBLOCK_ACCOUNT_BY_ID = "Error unblock bank account by id.";
    private static final String ERROR_FIND_ACCOUNT_BY_CARD_ID = "Error find account by card id.";
    private static final String ERROR_UPDATE_BALANCE = "Error update balance at credit card.";

    @Override
    public BankAccount create(BankAccount bankAccount) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, bankAccount.getCreditCard());
            preparedStatement.setString(2, bankAccount.getNameAccount());
            preparedStatement.setBoolean(3, bankAccount.isStatusBankAccount());
            preparedStatement.setBigDecimal(4, BigDecimal.valueOf(0));
            preparedStatement.setBoolean(5, true);
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_CREATE, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return bankAccount;
    }

    @Override
    public BankAccount update(BankAccount bankAccount, Long bankAccountId) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public BankAccount findById(Long bankAccountId) throws DAOException {
        BankAccount bankAccount = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, bankAccountId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    bankAccount = buildBankAccountRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_BY_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return bankAccount;
    }

    @Override
    public List<BankAccount> read() throws DAOException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount bankAccount;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_READ)) {
                while (resultSet.next()) {
                    bankAccount = buildBankAccountRowMapper(resultSet);
                    bankAccounts.add(bankAccount);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_READ, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return bankAccounts;
    }

    @Override
    public boolean deleteById(Long bankAccountId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, bankAccountId);
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_DELETE_BY_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public boolean bankAccountBlock(Long bankAccountId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_BLOCK_ACCOUNT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, bankAccountId);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_BLOCK_ACCOUNT_BY_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public boolean bankAccountUnBlock(Long bankAccountId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNBLOCK_ACCOUNT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, bankAccountId);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_UNBLOCK_ACCOUNT_BY_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public BankAccount findByCardId(Long creditCardId) throws DAOException {
        BankAccount bankAccount = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_CARD_ID)) {
            preparedStatement.setLong(1, creditCardId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    bankAccount = buildBankAccountRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_ACCOUNT_BY_CARD_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return bankAccount;
    }

    @Override
    public void updateBalance(BigDecimal newBalance, Long bankAccountId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE_BY_ACCOUNT_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setLong(2, bankAccountId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_UPDATE_BALANCE);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }
}
