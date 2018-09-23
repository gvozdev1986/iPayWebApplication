package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.MessageContactDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.MessageContactRowMapper;
import by.htp.hvozdzeu.model.MessageContact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MessageContactCrudImpl extends MessageContactRowMapper implements MessageContactDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageContactCrudImpl.class);

    private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`messages` " +
            "(`NameContact`, " +
            "`Date`, " +
            "`Time`, " +
            "`EmailContact`, " +
            "`PhoneContact`, " +
            "`MessageContact`, " +
            "`CheckRead`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_FIND_BY_ID = "SELECT " +
            "`messages`.`Id`, " +
            "`messages`.`NameContact`, " +
            "`messages`.`Date`, " +
            "`messages`.`Time`, " +
            "`messages`.`EmailContact`, " +
            "`messages`.`PhoneContact`, " +
            "`messages`.`MessageContact`, " +
            "`messages`.`CheckRead` " +
            "FROM `ipaywebapplication`.`messages` " +
            "WHERE `id`=?;";

    private static final String SQL_READ = "SELECT " +
            "`messages`.`Id`, " +
            "`messages`.`NameContact`, " +
            "`messages`.`Date`, " +
            "`messages`.`Time`, " +
            "`messages`.`EmailContact`, " +
            "`messages`.`PhoneContact`, " +
            "`messages`.`MessageContact`, " +
            "`messages`.`CheckRead` " +
            "FROM `ipaywebapplication`.`messages` ORDER BY `messages`.`CheckRead` ASC;";

    private static final String SQL_UNREAD_MESSAGE = "SELECT "
            + "messages.Id, "
            + "messages.NameContact, "
            + "messages.Date, "
            + "messages.Time, "
            + "messages.EmailContact, "
            + "messages.PhoneContact, "
            + "messages.MessageContact, "
            + "messages.CheckRead "
            + "FROM messages WHERE messages.CheckRead = ?;";

    private static final String SQL_CHECK_READ = "UPDATE " +
            "`ipaywebapplication`.`messages` SET `CheckRead`='1' WHERE  `Id`=?;";

    private static final String SQL_PAGINATION = "SELECT " +
            "messages.Id, " +
            "messages.NameContact, " +
            "messages.Date, " +
            "messages.Time, " +
            "messages.EmailContact, " +
            "messages.PhoneContact, " +
            "messages.MessageContact, " +
            "messages.CheckRead " +
            "FROM messages ORDER BY messages.CheckRead ASC LIMIT ?,?;";

    @Override
    public MessageContact create(MessageContact messageContact) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, messageContact.getNameContact());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setTime(3, Time.valueOf(LocalTime.now()));
            preparedStatement.setString(4, messageContact.getEmailContact());
            preparedStatement.setString(5, messageContact.getPhoneContact());
            preparedStatement.setString(6, messageContact.getMessageFromContact());
            preparedStatement.setBoolean(7, false);
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
        return messageContact;
    }

    @Override
    public MessageContact update(MessageContact messageContact, Long messageContactId) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public MessageContact findById(Long messageContactId) throws DAOException {
        MessageContact messageContact = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, messageContactId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    messageContact = buildMessageContactRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return messageContact;
    }

    @Override
    public List<MessageContact> read() throws DAOException {
        List<MessageContact> messageContacts = new ArrayList<>();
        MessageContact messageContact;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_READ)) {
                while (resultSet.next()) {
                    messageContact = buildMessageContactRowMapper(resultSet);
                    messageContacts.add(messageContact);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return messageContacts;
    }

    @Override
    public boolean deleteById(Long messageContactId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MessageContact> unreadMessages(boolean status) throws DAOException {
        List<MessageContact> messageContacts = new ArrayList<>();
        MessageContact messageContact;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNREAD_MESSAGE)) {
            preparedStatement.setBoolean(1, status);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    messageContact = buildMessageContactRowMapper(resultSet);
                    messageContacts.add(messageContact);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return messageContacts;
    }

    @Override
    public void checkMessageAsRead(Long messageContactId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_READ)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, messageContactId);
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }

    @Override
    public List<MessageContact> pagination(Integer start, Integer count) throws DAOException {
        List<MessageContact> messageContacts = new ArrayList<>();
        MessageContact messageContact;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_PAGINATION)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, start);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    messageContact = buildMessageContactRowMapper(resultSet);
                    messageContacts.add(messageContact);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return messageContacts;
    }
}
