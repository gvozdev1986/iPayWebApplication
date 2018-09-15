package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.IMessageContactDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.MessageContactRowMapper;
import by.htp.hvozdzeu.model.MessageContact;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MessageContactCrudImpl extends MessageContactRowMapper implements IMessageContactDAO {

    private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`messages` " +
            "(`NameContact`, " +
            "`Date`, " +
            "`Time`, " +
            "`EmailContact`, " +
            "`PhoneContact`, " +
            "`MessageContact`, " +
            "`CheckRead`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_BY_ID = "UPDATE `ipaywebapplication`.`messages` SET " +
            "`messages`.`NameContact`=?, " +
            "`messages`.`Date`=?, " +
            "`messages`.`Time`=?, " +
            "`messages`.`EmailContact`=?, " +
            "`messages`.`PhoneContact`=?, " +
            "`messages`.`MessageContact`=?, " +
            "`messages`.`CheckRead`=? " +
            "WHERE `Id`=?;";

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

    private static final String SQL_DELETE_BY_ID = "DELETE " +
            "FROM `ipaywebapplication`.`messages` " +
            "WHERE `id`=?;";

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
    public MessageContact create(MessageContact entity) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        //connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(1, entity.getNameContact());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setTime(3, Time.valueOf(LocalTime.now()));
            preparedStatement.setString(4, entity.getEmailContact());
            preparedStatement.setString(5, entity.getPhoneContact());
            preparedStatement.setString(6, entity.getMessageFromContact());
            preparedStatement.setBoolean(7, false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            //connection.rollback();
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public MessageContact update(MessageContact entity, Long id) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
            preparedStatement.setString(1, entity.getNameContact());
            preparedStatement.setString(2, entity.getEmailContact());
            preparedStatement.setString(3, entity.getPhoneContact());
            preparedStatement.setString(4, entity.getMessageFromContact());
            preparedStatement.setBoolean(5, entity.isCheckRead());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public MessageContact findById(Long id) throws DAOException {
        MessageContact messageContact = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
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
    public boolean deleteById(Long id) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
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
    public boolean checkMessageAsRead(Long messageId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_READ)) {
            preparedStatement.setLong(1, messageId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
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
