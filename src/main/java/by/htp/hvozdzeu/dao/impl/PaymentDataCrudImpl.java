package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.IPaymentDataDAO;
import by.htp.hvozdzeu.dao.connection.exception.ConnectionException;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.PaymentDataRowMapper;
import by.htp.hvozdzeu.model.PaymentData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDataCrudImpl extends PaymentDataRowMapper implements IPaymentDataDAO {

	private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`paymentdata` " + "(`PaymentDataCode`, "
			+ "`PaymentDataName`, " + "`PaymentDataGroup`, " + "`PaymentDataDescription`, " + "`Available`) "
			+ "VALUES (?, ?, ?, ?, ?);";

	private static final String SQL_UPDATE_BY_ID = "UPDATE `ipaywebapplication`.`paymentdata` SET "
			+ "`PaymentDataCode`= ?, " + "`PaymentDataName`= ?, " + "`PaymentDataGroup`= ?, "
			+ "`PaymentDataDescription`= ? " + "WHERE  `Id`= ?;";

	private static final String SQL_FIND_BY_ID = "SELECT " + "`Id`, " + "`PaymentDataCode`, " + "`PaymentDataName`, "
			+ "`PaymentDataGroup`, " + "`PaymentDataDescription`, " + "`Available` "
			+ "FROM `ipaywebapplication`.`paymentdata` WHERE id = ?;";

	private static final String SQL_READ = "SELECT " + "`Id`, " + "`PaymentDataCode`, " + "`PaymentDataName`, "
			+ "`PaymentDataGroup`, " + "`PaymentDataDescription`, " + "`Available` "
			+ "FROM `ipaywebapplication`.`paymentdata`;";

    private static final String SQL_PAGINATION = "SELECT " + "`Id`, " + "`PaymentDataCode`, " + "`PaymentDataName`, "
            + "`PaymentDataGroup`, " + "`PaymentDataDescription`, " + "`Available` "
            + "FROM `ipaywebapplication`.`paymentdata` LIMIT ?, ?;";

	private static final String SQL_DELETE_BY_ID = "UPDATE `ipaywebapplication`.`paymentdata` SET `Available`='0' WHERE  `Id`= ?;";

    private static final String SQL_PARAMETERS = "SELECT " +
            "paymentdata.Id, " +
            "paymentdata.PaymentDataCode, " +
            "paymentdata.PaymentDataName, " +
            "paymentdata.PaymentDataGroup, " +
            "paymentdata.PaymentDataDescription, " +
            "paymentdata.Available " +
            "FROM paymentdata " +
            "WHERE concat(paymentdata.PaymentDataCode, paymentdata.PaymentDataName, paymentdata.PaymentDataGroup, paymentdata.PaymentDataDescription) like ?;";

    private static final String SQL_MAX_ID = "SELECT MAX(`id`) AS MaxID FROM `paymentdata`;";

	private static final String ERROR_CREATE = "Error create payment data.";
	private static final String ERROR_UPDATE_BY_ID = "Error update payment data by id.";
	private static final String ERROR_FIND_BY_ID = "Error find payment data by id.";
	private static final String ERROR_READ = "Error read payment dates.";
	private static final String ERROR_DELETE_BY_ID = "Error delete payment data by id.";


	@Override
	public PaymentData create(PaymentData entity) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
			preparedStatement.setString(1, entity.getPaymentDataCode());
			preparedStatement.setString(2, entity.getPaymentDataName());
			preparedStatement.setString(3, entity.getPaymentDataGroup());
			preparedStatement.setString(4, entity.getPaymentDataDescription());
			preparedStatement.setBoolean(5, true);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ERROR_CREATE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return entity;
	}

	@Override
	public PaymentData update(PaymentData entity, Long id) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
			preparedStatement.setString(1, entity.getPaymentDataCode());
			preparedStatement.setString(2, entity.getPaymentDataName());
			preparedStatement.setString(3, entity.getPaymentDataGroup());
			preparedStatement.setString(4, entity.getPaymentDataDescription());
			preparedStatement.setLong(5, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ERROR_UPDATE_BY_ID, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return entity;
	}

	@Override
	public PaymentData findById(Long id) throws DAOException {
		PaymentData paymentData = null;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					paymentData = buildPaymentServiceRowMapper(resultSet);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_FIND_BY_ID, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return paymentData;
	}

	@Override
	public List<PaymentData> read() throws DAOException {
		List<PaymentData> paymentDates = new ArrayList<>();
		PaymentData paymentData;
		Connection connection = dataBaseConnection.getConnection();
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_READ)) {
				while (resultSet.next()) {
					paymentData = buildPaymentServiceRowMapper(resultSet);
					paymentDates.add(paymentData);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_READ, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return paymentDates;
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
	public List<PaymentData> pagination(Integer start, Integer count) throws DAOException {
        List<PaymentData> paymentDataList = new ArrayList<>();
        PaymentData paymentData;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_PAGINATION)) {
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, start);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    paymentData = buildPaymentServiceRowMapper(resultSet);
                    paymentDataList.add(paymentData);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return paymentDataList;
	}

    @Override
    public List<PaymentData> findByParameter(String param) throws DAOException {
        List<PaymentData> paymentDataList = new ArrayList<>();
        PaymentData paymentData;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_PARAMETERS)) {
            preparedStatement.setString(1, "%" + param + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    paymentData = buildPaymentServiceRowMapper(resultSet);
                    paymentDataList.add(paymentData);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return paymentDataList;
    }

    @Override
    public Long maxIndex() throws DAOException {
        Long maxIndex = null;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_MAX_ID)) {
                while (resultSet.next()) {
                    maxIndex = resultSet.getLong("MaxID");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return maxIndex;
    }
}
