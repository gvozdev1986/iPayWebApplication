package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.IClientDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.ClientRowMapper;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.util.PasswordEncoder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientCrudImpl extends ClientRowMapper implements IClientDAO {

	private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`client` " 
			+ "(`Login`, "
			+ "`Password`, " 
			+ "`FirstName`, " 
			+ "`LastName`, " 
			+ "`Patronymic`, " 
			+ "`DateBirth`, " 
			+ "`PhoneHome`, "
			+ "`PhoneMobile`, " 
			+ "`Address`, " 
			+ "`Role`, " 
			+ "`Email`, " 
			+ "`Available`, " 
			+ "`isOnline`)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SQL_UPDATE_BY_ID = "UPDATE `ipaywebapplication`.`client` SET "
			+ "`FirstName`=?, "
			+ "`LastName`=?, "
			+ "`Patronymic`=?, "
			+ "`PhoneHome`=?, "
			+ "`DateBirth`=?, "
			+ "`PhoneMobile`=?, "
			+ "`Address`=?, "
			+ "`Email`=? "
			+ "WHERE  `Id`=?;";

	private static final String SQL_FIND_BY_ID = "SELECT " + "`Id`, " + "`Login`, " + "`Password`, " + "`FirstName`, "
			+ "`LastName`, " + "`Patronymic`, " + "`DateBirth`, " + "`PhoneHome`, " + "`PhoneMobile`, " + "`Address`, "
			+ "`Role`, " + "`Email`, " + "`Available`, " + "`isOnline` "
			+ "FROM `ipaywebapplication`.`client` WHERE `id` = ?";

	private static final String SQL_FIND_BY_LAST_NAME = "SELECT " + "`Id`, " + "`Login`, " + "`Password`, "
			+ "`FirstName`, " + "`LastName`, " + "`Patronymic`, " + "`DateBirth`, " + "`PhoneHome`, "
			+ "`PhoneMobile`, " + "`Address`, " + "`Role`, " + "`Email`, " + "`Available`, " + "`isOnline` "
			+ "FROM `ipaywebapplication`.`client` WHERE `LastName` = ?";

	private static final String CHECK_ACCOUNT = "SELECT " + "`Id`, " + "`Login`, " + "`Password`, " + "`FirstName`, "
			+ "`LastName`, " + "`Patronymic`, " + "`DateBirth`, " + "`PhoneHome`, " + "`PhoneMobile`, " + "`Address`, "
			+ "`Role`, " + "`Email`, " + "`Available`, " + "`isOnline` "
			+ "FROM `ipaywebapplication`.`client` WHERE `Login` = ? AND `Password` = ?;";

	private static final String SQL_READ = "SELECT " + "`Id`, " + "`Login`, " + "`Password`, " + "`FirstName`, "
			+ "`LastName`, " + "`Patronymic`, " + "`DateBirth`, " + "`PhoneHome`, " + "`PhoneMobile`, " + "`Address`, "
			+ "`Role`, " + "`Email`, " + "`Available`, " + "`isOnline` " + "FROM `ipaywebapplication`.`client`";

	private static final String SQL_DELETE_BY_ID = "UPDATE `ipaywebapplication`.`client` SET"
			+ "`Available` = 0 WHERE `Id` = ?;";

	private static final String SQL_UPDATE_PSWD = "UPDATE `ipaywebapplication`.`client` SET "
			+ "`Password`= ? WHERE `Id`= ?;";

	private static final String SQL_IS_ONLINE_UPDATE = "UPDATE "
			+ "`ipaywebapplication`.`client` SET `isOnline`='1' WHERE  `Id`=?;";

	private static final String SQL_IS_OFFLINE_UPDATE = "UPDATE "
			+ "`ipaywebapplication`.`client` SET `isOnline`='0' WHERE  `Id`=?;";

	private static final String SQL_LIST_BLOCKED_CLIENT = "SELECT " 
			+ "`Id`, " 
			+ "`Login`, " 
			+ "`Password`, " 
			+ "`FirstName`, "
			+ "`LastName`, " 
			+ "`Patronymic`, " 
			+ "`DateBirth`, " 
			+ "`PhoneHome`, " 
			+ "`PhoneMobile`, " 
			+ "`Address`, "
			+ "`Role`, " 
			+ "`Email`, " 
			+ "`Available`, " 
			+ "`isOnline` " 
			+ "FROM `ipaywebapplication`.`client` WHERE `client`.`Available` = false;";
	
	private static final String SQL_PAGINATION = "SELECT "
			+ "`Id`, "
			+ "`Login`, "
			+ "`Password`, "
			+ "`FirstName`, "
			+ "`LastName`, "
			+ "`Patronymic`, "
			+ "`DateBirth`, "
			+ "`PhoneHome`, "
			+ "`PhoneMobile`, "
			+ "`Address`, "
			+ "`Role`, " 
			+ "`Email`, "
			+ "`Available`, "
			+ "`isOnline` FROM `ipaywebapplication`.`client` LIMIT ?, ?;";
	
	private static final String ERROR_UPDATE_BY_ID = "Error update message.";
	private static final String ERROR_CREATE = "Error create message.";
	private static final String ERROR_READ = "Error read from clients table.";
	private static final String ERROR_FIND_BY_ID = "Error find message by id.";
	private static final String ERROR_DELETE_BY_ID = "Error delete message by id.";
	private static final String ERROR_FIND_BY_LAST_NAME = "Error find message by last name.";
	private static final String ERROR_CHECK_ACCOUNT = "Error check account by clients";
	private static final String ERROR_UPDATE_AS_ONLINE = "Error update client as online.";
	private static final String ERROR_UPDATE_AS_OFFLINE = "Error update client as offline.";
	private static final String ERROR_LIST_BLOCKED_CLIENT = "Error getting list blocked client.";
	private static final String ERROR_PAGINATION = "Error getting pagination.";

	@Override
	public Client create(Client entity) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
			String encodedPassword = passwordEncoder.getEncodeData(rebasePassword.rebasePSWD(entity.getPassword()));
			preparedStatement.setString(1, entity.getLogin());
			preparedStatement.setString(2, encodedPassword);
			preparedStatement.setString(3, entity.getFirstName());
			preparedStatement.setString(4, entity.getLastName());
			preparedStatement.setString(5, entity.getPatronymic());
			preparedStatement.setDate(6, Date.valueOf(entity.getDateBirth()));
			preparedStatement.setString(7, entity.getPhoneHome());
			preparedStatement.setString(8, entity.getPhoneMobile());
			preparedStatement.setString(9, entity.getAddress());
			preparedStatement.setString(10, "CLIENT");
			preparedStatement.setString(11, entity.getEmail());
			preparedStatement.setBoolean(12, true);
			preparedStatement.setBoolean(13, false);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ERROR_CREATE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return entity;
	}

	@Override
	public Client update(Client entity, Long id) throws DAOException {		
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setString(3, entity.getPatronymic());
			preparedStatement.setString(4, entity.getPhoneHome());			
			preparedStatement.setDate(5, Date.valueOf(entity.getDateBirth()));			
			preparedStatement.setString(6, entity.getPhoneMobile());
			preparedStatement.setString(7, entity.getAddress());
			preparedStatement.setString(8, entity.getEmail());
			preparedStatement.setLong(9, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ERROR_UPDATE_BY_ID, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return entity;
	}

	@Override
	public Client findById(Long id) throws DAOException {
		Client client = null;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					client = buildClientRowMapper(resultSet);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_FIND_BY_ID, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return client;
	}

	@Override
	public List<Client> read() throws DAOException {
		List<Client> clients = new ArrayList<>();
		Client client;
		Connection connection = dataBaseConnection.getConnection();
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_READ)) {
				while (resultSet.next()) {
					client = buildClientRowMapper(resultSet);
					clients.add(client);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_READ, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return clients;
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
	public Client checkAccount(String user, String pswd) throws DAOException {
		Client client = null;
		Connection connection = dataBaseConnection.getConnection();
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT)) {
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, passwordEncoder.getEncodeData(pswd));
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					client = buildClientRowMapper(resultSet);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_CHECK_ACCOUNT, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return client;
	}

	@Override
	public Client findByLastName(String lastName) throws DAOException {
		Client client = null;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LAST_NAME)) {
			preparedStatement.setString(1, lastName);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					client = buildClientRowMapper(resultSet);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_FIND_BY_LAST_NAME, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return client;
	}

	@Override
	public boolean updatePassword(Long id, String password) throws DAOException {
		
		System.out.println("ID" + id);
		System.out.println("PASSWORD" + password);
		
		Connection connection = dataBaseConnection.getConnection();
		PasswordEncoder encoder = new PasswordEncoder();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PSWD)) {
			preparedStatement.setString(1, String.valueOf(rebasePassword.rebasePSWD(encoder.getEncodeData(password))));
			preparedStatement.setLong(2, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean isOnlineUpdate(Long id) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_ONLINE_UPDATE)) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException(ERROR_UPDATE_AS_ONLINE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean isOffLineUpdate(Long id) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_OFFLINE_UPDATE)) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException(ERROR_UPDATE_AS_OFFLINE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
	}

	@Override
	public List<Client> blockedClient() throws DAOException {
		List<Client> clients = new ArrayList<>();
		Client client;
		Connection connection = dataBaseConnection.getConnection();
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_LIST_BLOCKED_CLIENT)) {
				while (resultSet.next()) {
					client = buildClientRowMapper(resultSet);
					clients.add(client);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_LIST_BLOCKED_CLIENT, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return clients;
	}

	@Override
	public List<Client> pagination(Integer start, Integer count) throws DAOException {
		List<Client> clients = new ArrayList<>();
		Client client;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_PAGINATION)) {
			preparedStatement.setInt(1, count);
			preparedStatement.setInt(2, start);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					client = buildClientRowMapper(resultSet);
					clients.add(client);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_PAGINATION, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return clients;
	}

}
