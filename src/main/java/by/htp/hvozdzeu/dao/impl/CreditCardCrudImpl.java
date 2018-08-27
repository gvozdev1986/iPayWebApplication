package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.ICreditCardDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.CreditCardRowMapper;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.StatusCardReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardCrudImpl extends CreditCardRowMapper implements ICreditCardDAO {

	private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`creditcard` " + "(" + "`Client`, "
			+ "`CardNumber`, " + "`CardFirstName`, " + "`CardLastName`, " + "`ValidDate`, " + "`TypeCard`, "
			+ "`VerifyCode`, " + "`Block`, " + "`Available` " + ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SQL_UPDATE_BY_ID = "UPDATE `ipaywebapplication`.`creditcard` SET "
			+ "`CardFirstName`= ?, `CardLastName`= ? WHERE  `Id`= ?;";

	private static final String SQL_READ = "SELECT " + "`Id`, " + "`Client`, " + "`CardNumber`, " + "`CardFirstName`, "
			+ "`CardLastName`, " + "`ValidDate`, " + "`TypeCard`, " + "`VerifyCode`, " + "`Block`, " + "`Available` "
			+ "FROM `ipaywebapplication`.`creditcard`;";

	private static final String SQL_FIND_BY_ID = "SELECT " + "`Id`, " + "`Client`, " + "`CardNumber`, "
			+ "`CardFirstName`, " + "`CardLastName`, " + "`ValidDate`, " + "`TypeCard`, " + "`VerifyCode`, "
			+ "`Block`, " + "`Available` " + "FROM `ipaywebapplication`.`creditcard` WHERE `id` = ?;";

	private static final String SQL_FIND_BY_CREDIT_CARD_NUMBER = "SELECT " + "`Id`, " + "`Client`, " + "`CardNumber`, "
			+ "`CardFirstName`, " + "`CardLastName`, " + "`ValidDate`, " + "`TypeCard`, " + "`VerifyCode`, "
			+ "`Block`, " + "`Available` " + "FROM `ipaywebapplication`.`creditcard` WHERE `CardNumber` = ?;";

	private static final String SQL_DELETE_BY_ID = "UPDATE `ipaywebapplication`.`creditcard` SET "
			+ "`Available`= 0 WHERE  `Id`= ?; ";

	private static final String SQL_FIND_BY_CLIENT_ID = "SELECT " + "creditcard.Id, " + "CardNumber, "
			+ "CardFirstName, " + "CardLastName, " + "ValidDate, " + "TypeCard, " + "VerifyCode, " + "Block, "
			+ "BalanceBankAccount, " + "NameAccount "
			+ "FROM creditcard JOIN bankaccount ON bankaccount.CreditCard = creditcard.Id WHERE creditcard.Client = ?;";

	private static final String SQL_BLOCK_CARD = "UPDATE `ipaywebapplication`.`creditcard` SET `Block`='1' WHERE `Id`= ?;";

	private static final String SQL_UNBLOCK_CARD = "UPDATE `ipaywebapplication`.`creditcard` SET `Block`='0' WHERE `Id`= ?;";

	private static final String SQL_LIST_BLOCKED_CARD = "SELECT " + "`Id`, " + "`Client`, " + "`CardNumber`, "
			+ "`CardFirstName`, " + "`CardLastName`, " + "`ValidDate`, " + "`TypeCard`, " + "`VerifyCode`, "
			+ "`Block`, " + "`Available` "
			+ "FROM `ipaywebapplication`.`creditcard` WHERE `creditcard`.`Block` = true;";

	private static final String SQL_FIND_BY_PARAMETER = "SELECT " + "`Id`, " + "`Client`, " + "`CardNumber`, "
			+ "`CardFirstName`, " + "`CardLastName`, " + "`ValidDate`, " + "`TypeCard`, " + "`VerifyCode`, "
			+ "`Block`, " + "`Available` " + "FROM creditcard WHERE creditcard.CardFirstName = ? "
			+ "OR creditcard.CardLastName = ?" + "OR creditcard.CardNumber = ?;";

	private static final String SQL_FIND_BLOCKED_BY_PARAMETER = "SELECT " + "`Id`, " + "`Client`, " + "`CardNumber`, "
			+ "`CardFirstName`, " + "`CardLastName`, " + "`ValidDate`, " + "`TypeCard`, " + "`VerifyCode`, "
			+ "`Block`, " + "`Available` " + "FROM creditcard WHERE creditcard.CardFirstName = ? "
			+ "OR creditcard.CardLastName = ?" + "OR creditcard.CardNumber = ? AND creditcard.Block = true;";

	private static final String SQL_PAGINATION = "SELECT " + "`Id`, " + "`Client`, " + "`CardNumber`, " + "`CardFirstName`, "
			+ "`CardLastName`, " + "`ValidDate`, " + "`TypeCard`, " + "`VerifyCode`, " + "`Block`, " + "`Available` "
			+ "FROM `ipaywebapplication`.`creditcard` LIMIT ? OFFSET ?;";
	
	private static final String ERROR_CREATE = "Error create message.";
	private static final String ERROR_UPDATE_BY_ID = "Error update message.";
	private static final String ERROR_READ = "Error read from clients table.";
	private static final String ERROR_FIND_BY_ID = "Error find message by id.";
	private static final String ERROR_DELETE_BY_ID = "Error delete message by id.";
	private static final String ERROR_FIND_BY_CARD_NUMBER = "Error find message by card number.";
	private static final String ERROR_FIND_CARD_BY_CLIENT_ID = "Error find credit card by id client";
	private static final String ERROR_BLOCK_CARD = "Error block credit card;";
	private static final String ERROR_UNBLOCK_CARD = "Error block credit card;";
	private static final String ERROR_LIST_BLOCKED_CARD = "Error getting blcked credit card;";
	private static final String ERROR_FIND_BY_PARAMETER = "Error find credit card by parameter";
	private static final String ERROR_FIND_BLOCKED_BY_PARAMETER = "Error find bloked credit card by parameter";
	private static final String ERROR_PAGINATION = "Error view pagination";

	@Override
	public CreditCard create(CreditCard entity) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
			preparedStatement.setLong(1, entity.getClient());
			preparedStatement.setString(2, entity.getCardNumber());
			preparedStatement.setString(3, entity.getCardFirstName());
			preparedStatement.setString(4, entity.getCardLastName());
			preparedStatement.setString(5, entity.getValidDate());
			preparedStatement.setString(6, "UNDEFINED");
			preparedStatement.setString(7, entity.getVerifyCode());
			preparedStatement.setBoolean(8, true);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ERROR_CREATE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return entity;
	}

	@Override
	public CreditCard update(CreditCard entity, Long id) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
			preparedStatement.setString(1, entity.getCardFirstName());
			preparedStatement.setString(2, entity.getCardLastName());
			preparedStatement.setLong(3, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ERROR_UPDATE_BY_ID, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return entity;
	}

	@Override
	public CreditCard findById(Long id) throws DAOException {
		CreditCard creditCard = null;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			preparedStatement.setLong(1, id);
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
	public CreditCard findByCreditCardNumber(String creditCard) throws DAOException {
		CreditCard card = null;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CREDIT_CARD_NUMBER)) {
			preparedStatement.setString(1, creditCard);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					card = buildCreditCardRowMapper(resultSet);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_FIND_BY_CARD_NUMBER, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return card;
	}

	@Override
	public List<StatusCardReport> findCreditCardByIdClient(Long clientId) throws DAOException {
		List<StatusCardReport> statusCardReports = new ArrayList<>();
		StatusCardReport statusCardReport;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CLIENT_ID)) {
			preparedStatement.setLong(1, clientId);
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
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_BLOCK_CARD)) {
			preparedStatement.setLong(1, creditCardId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException(ERROR_BLOCK_CARD, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean unblockCreditCard(Long creditCardId) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNBLOCK_CARD)) {
			preparedStatement.setLong(1, creditCardId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException(ERROR_UNBLOCK_CARD, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
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

}
