package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.IMessageContactDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.MessageContactRowMapper;
import by.htp.hvozdzeu.model.MessageContact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageContactCrudImpl extends MessageContactRowMapper implements IMessageContactDAO {

    private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`sengmessage` " +
            "(`NameContact`, " +
            "`EmailContact`, " +
            "`PhoneContact`, " +
            "`MessageContact`, " +
            "`ReadStatus`) " +
            "VALUES (?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_BY_ID = "UPDATE `ipaywebapplication`.`sengmessage` SET " +
            "`NameContact`=?, " +
            "`EmailContact`=?, " +
            "`PhoneContact`=?, " +
            "`MessageContact`=?, " +
            "`ReadStatus`=? " +
            "WHERE `Id`=?;";

    private static final String SQL_FIND_BY_ID = "SELECT " +
            "`Id`, " +
            "`NameContact`, " +
            "`EmailContact`, " +
            "`PhoneContact`, " +
            "`MessageContact`, " +
            "`ReadStatus` " +
            "FROM `ipaywebapplication`.`sengmessage` " +
            "WHERE `id`=?;";

    private static final String SQL_READ = "SELECT " +
            "`Id`, " +
            "`NameContact`, " +
            "`EmailContact`, " +
            "`PhoneContact`, " +
            "`MessageContact`, " +
            "`ReadStatus` " +
            "FROM `ipaywebapplication`.`sengmessage` WHERE `ReadStatus` = false;";

    private static final String SQL_DELETE_BY_ID = "DELETE " +
            "`NameContact`, " +
            "`EmailContact`, " +
            "`PhoneContact`, " +
            "`MessageContact`, " +
            "`ReadStatus` " +
            "FROM `ipaywebapplication`.`sengmessage` " +
            "WHERE `id`=?;";
    
    private static final String SQL_UNREAD_MESSAGE = "SELECT "
    		+ "sengmessage.Id, "
    		+ "sengmessage.NameContact, "
    		+ "sengmessage.EmailContact, " 
    		+ "sengmessage.PhoneContact, "
    		+ "sengmessage.MessageContact, "
    		+ "sengmessage.ReadStatus "
    		+ "FROM sengmessage WHERE sengmessage.ReadStatus = ?;";

    private static final String ERROR_UPDATE_BY_ID = "Error update message.";
    private static final String ERROR_CREATE = "Error create message.";
    private static final String ERROR_READ = "Error read from messages table.";
    private static final String ERROR_FIND_BY_ID = "Error find message by id.";
    private static final String ERROR_DELETE_BY_ID = "Error delete message by id.";
    private static final String ERROR_UNREAD_MESSAGE = "Error getting unread messages";

    @Override
    public MessageContact create(MessageContact entity) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(1, entity.getNameContact());
            preparedStatement.setString(2, entity.getEmailContact());
            preparedStatement.setString(3, entity.getPhoneContact());
            preparedStatement.setString(4, entity.getMessageFromContact());
            preparedStatement.setBoolean(5, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_CREATE, e);
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
            preparedStatement.setBoolean(5, entity.isRead());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(ERROR_UPDATE_BY_ID, e);
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
            throw new DAOException(ERROR_FIND_BY_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return messageContact;
    }

    @Override
    public List<MessageContact> read() throws DAOException {
        List<MessageContact> bankAccounts = new ArrayList<>();
        MessageContact messageContact;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_READ)) {
                while (resultSet.next()) {
                    messageContact = buildMessageContactRowMapper(resultSet);
                    bankAccounts.add(messageContact);
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
    public boolean deleteById(Long id) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(ERROR_DELETE_BY_ID, e);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }

	@Override
	public List<MessageContact> unreadmessages(boolean status) throws DAOException {
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
			throw new DAOException(ERROR_UNREAD_MESSAGE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return messageContacts;
	}
}
