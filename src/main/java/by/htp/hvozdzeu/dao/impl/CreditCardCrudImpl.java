package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.CreditCardDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.CreditCardRowMapper;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardCrudImpl extends CreditCardRowMapper implements CreditCardDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardCrudImpl.class);


    private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`creditcard` "
            + "("
            + "`Client`, "
            + "`CardNumber`, "
            + "`CardFirstName`, "
            + "`CardLastName`, "
            + "`ValidDate`, "
            + "`TypeCard`, "
            + "`VerifyCode`, "
            + "`Block`, "
            + "`Available` "
            + ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_READ = "SELECT "
            + "`Id`, "
            + "`Client`, "
            + "`CardNumber`, "
            + "`CardFirstName`, "
            + "`CardLastName`, "
            + "`ValidDate`, "
            + "`TypeCard`, "
            + "`VerifyCode`, "
            + "`Block`, "
            + "`Available` "
            + "FROM `ipaywebapplication`.`creditcard`;";

    private static final String SQL_FIND_BY_ID = "SELECT "
            + "`Id`, "
            + "`Client`, "
            + "`CardNumber`, "
            + "`CardFirstName`, "
            + "`CardLastName`, "
            + "`ValidDate`, "
            + "`TypeCard`, "
            + "`VerifyCode`, "
            + "`Block`, "
            + "`Available` "
            + "FROM `ipaywebapplication`.`creditcard` WHERE `id` = ?;";

    private static final String SQL_FIND_BY_CREDIT_CARD_NUMBER = "SELECT "
            + "`Id`, "
            + "`Client`, "
            + "`CardNumber`, "
            + "`CardFirstName`, "
            + "`CardLastName`, "
            + "`ValidDate`, "
            + "`TypeCard`, "
            + "`VerifyCode`, "
            + "`Block`, "
            + "`Available` "
            + "FROM `ipaywebapplication`.`creditcard` WHERE `CardNumber` = ?;";

    private static final String SQL_DELETE_DO_AVAILABLE_BY_ID = "UPDATE `ipaywebapplication`.`creditcard` SET "
            + "`Available`= 0 WHERE  `Id`= ?; ";

    private static final String SQL_FIND_BY_CLIENT_ID = "SELECT "
            + "creditcard.Id, "
            + "CardNumber, "
            + "CardFirstName, "
            + "CardLastName, "
            + "ValidDate, "
            + "TypeCard, "
            + "VerifyCode, "
            + "Block, "
            + "BalanceBankAccount, "
            + "NameAccount "
            + "FROM creditcard JOIN bankaccount ON bankaccount.CreditCard = creditcard.Id WHERE creditcard.Client = ?;";

    private static final String SQL_BLOCK_CARD = "UPDATE `ipaywebapplication`.`creditcard` SET `Block`='1' WHERE `Id`= ?;";

    private static final String SQL_UNBLOCK_CARD = "UPDATE `ipaywebapplication`.`creditcard` SET `Block`='0' WHERE `Id`= ?;";

    private static final String SQL_LIST_BLOCKED_CARD = "SELECT "
            + "`Id`, "
            + "`Client`, "
            + "`CardNumber`, "
            + "`CardFirstName`, "
            + "`CardLastName`, "
            + "`ValidDate`, "
            + "`TypeCard`, "
            + "`VerifyCode`, "
            + "`Block`, "
            + "`Available` "
            + "FROM `ipaywebapplication`.`creditcard` WHERE `creditcard`.`Block` = true;";

    private static final String SQL_FIND_BY_PARAMETER = "SELECT "
            + "`Id`, "
            + "`Client`, "
            + "`CardNumber`, "
            + "`CardFirstName`, "
            + "`CardLastName`, "
            + "`ValidDate`, "
            + "`TypeCard`, "
            + "`VerifyCode`, "
            + "`Block`, "
            + "`Available` "
            + "FROM creditcard WHERE creditcard.CardFirstName = ? "
            + "OR creditcard.CardLastName = ?"
            + "OR creditcard.CardNumber = ?;";

    private static final String SQL_FIND_BLOCKED_BY_PARAMETER = "SELECT "
            + "`Id`, "
            + "`Client`, "
            + "`CardNumber`, "
            + "`CardFirstName`, "
            + "`CardLastName`, "
            + "`ValidDate`, "
            + "`TypeCard`, "
            + "`VerifyCode`, "
            + "`Block`, "
            + "`Available` "
            + "FROM creditcard WHERE creditcard.CardFirstName = ? "
            + "OR creditcard.CardLastName = ?"
            + "OR creditcard.CardNumber = ? AND creditcard.Block = true;";

    private static final String SQL_PAGINATION = "SELECT "
            + "`Id`, "
            + "`Client`, "
            + "`CardNumber`, "
            + "`CardFirstName`, "
            + "`CardLastName`, "
            + "`ValidDate`, "
            + "`TypeCard`, "
            + "`VerifyCode`, "
            + "`Block`, "
            + "`Available` "
            + "FROM `ipaywebapplication`.`creditcard` LIMIT ?, ?;";

    private static final String ERROR_CREATE = "Error save message.";
    private static final String ERROR_READ = "Error getAllUsers from clients table.";
    private static final String ERROR_FIND_BY_ID = "Error find message by id.";
    private static final String ERROR_DELETE_BY_ID = "Error delete message by id.";
    private static final String ERROR_FIND_BY_CARD_NUMBER = "Error find message by card number.";
    private static final String ERROR_FIND_CARD_BY_CLIENT_ID = "Error find credit card by id user";
    private static final String ERROR_BLOCK_CARD = "Error block credit card;";
    private static final String ERROR_UNBLOCK_CARD = "Error block credit card;";
    private static final String ERROR_LIST_BLOCKED_CARD = "Error getting blcked credit card;";
    private static final String ERROR_FIND_BY_PARAMETER = "Error find credit card by parameter";
    private static final String ERROR_FIND_BLOCKED_BY_PARAMETER = "Error find bloked credit card by parameter";
    private static final String ERROR_PAGINATION = "Error view pagination";

    @Override
    public CreditCard create(CreditCard creditCard) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, creditCard.getClient());
            preparedStatement.setString(2, creditCard.getCardNumber());
            preparedStatement.setString(3, creditCard.getCardFirstName());
            preparedStatement.setString(4, creditCard.getCardLastName());
            preparedStatement.setString(5, creditCard.getValidDate());
            preparedStatement.setString(6, "UNDEFINED");
            preparedStatement.setString(7, creditCard.getVerifyCode());
            preparedStatement.setBoolean(8, true);
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
        return creditCard;
    }

    @Override
    public CreditCard update(CreditCard creditCard, Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CreditCard findById(Long creditCardId) throws DAOException {
        CreditCard creditCard = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, creditCardId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    creditCard = buildCreditCardRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_BY_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return creditCard;
    }

    @Override
    public List<CreditCard> read() throws DAOException {
        List<CreditCard> creditCards = new ArrayList<>();
        CreditCard creditCard;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_READ)) {
                while (resultSet.next()) {
                    creditCard = buildCreditCardRowMapper(resultSet);
                    creditCards.add(creditCard);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_READ, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return creditCards;
    }

    @Override
    public boolean deleteById(Long creditCardId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_DO_AVAILABLE_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, creditCardId);
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
    public CreditCard findByCreditCardNumber(String creditCardNumber) throws DAOException {
        CreditCard creditCard = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CREDIT_CARD_NUMBER)) {
            preparedStatement.setString(1, creditCardNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    creditCard = buildCreditCardRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_BY_CARD_NUMBER, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return creditCard;
    }

    @Override
    public List<StatusCardReport> findCreditCardByIdClient(Long userId) throws DAOException {
        List<StatusCardReport> statusCardReports = new ArrayList<>();
        StatusCardReport statusCardReport;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CLIENT_ID)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    statusCardReport = buildStatusCreditCardRowMapper(resultSet);
                    statusCardReports.add(statusCardReport);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_CARD_BY_CLIENT_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return statusCardReports;
    }

    @Override
    public boolean blockCreditCard(Long creditCardId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_BLOCK_CARD)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, creditCardId);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_BLOCK_CARD, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public boolean unblockCreditCard(Long creditCardId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNBLOCK_CARD)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, creditCardId);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_UNBLOCK_CARD, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<CreditCard> blockedCreditCard() throws DAOException {
        List<CreditCard> creditCards = new ArrayList<>();
        CreditCard creditCard;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_LIST_BLOCKED_CARD)) {
                while (resultSet.next()) {
                    creditCard = buildCreditCardRowMapper(resultSet);
                    creditCards.add(creditCard);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_LIST_BLOCKED_CARD, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return creditCards;
    }

    @Override
    public List<CreditCard> findByParameter(String param) throws DAOException {
        List<CreditCard> creditCards = new ArrayList<>();
        CreditCard creditCard;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PARAMETER)) {
            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param);
            preparedStatement.setString(3, param);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    creditCard = buildCreditCardRowMapper(resultSet);
                    creditCards.add(creditCard);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_BY_PARAMETER, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return creditCards;
    }

    @Override
    public List<CreditCard> findBlockedByParameter(String param) throws DAOException {
        List<CreditCard> creditCards = new ArrayList<>();
        CreditCard creditCard;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BLOCKED_BY_PARAMETER)) {
            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param);
            preparedStatement.setString(3, param);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    creditCard = buildCreditCardRowMapper(resultSet);
                    creditCards.add(creditCard);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_BLOCKED_BY_PARAMETER, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return creditCards;
    }

    @Override
    public List<CreditCard> pagination(Integer start, Integer count) throws DAOException {
        List<CreditCard> creditCards = new ArrayList<>();
        CreditCard creditCard;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_PAGINATION)) {
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, start);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    creditCard = buildCreditCardRowMapper(resultSet);
                    creditCards.add(creditCard);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_PAGINATION, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return creditCards;
    }

    @Override
    public Long createReturnId(CreditCard creditCard) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Long creditCardId;
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, creditCard.getClient());
            preparedStatement.setString(2, creditCard.getCardNumber());
            preparedStatement.setString(3, creditCard.getCardFirstName());
            preparedStatement.setString(4, creditCard.getCardLastName());
            preparedStatement.setString(5, creditCard.getValidDate());
            preparedStatement.setString(6, String.valueOf(creditCard.getTypeCard()));
            preparedStatement.setString(7, creditCard.getVerifyCode());
            preparedStatement.setBoolean(8, creditCard.isBlock());
            preparedStatement.setBoolean(9, true);
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    creditCardId = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating credit card failed, no ID obtained.");
                }
            }

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
        return creditCardId;
    }

}
