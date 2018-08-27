package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.IPaymentDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.PaymentRowMapper;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentCrudImpl extends PaymentRowMapper implements IPaymentDAO {

	private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`payment` " + "(`DatePayment`, "
			+ "`TimePayment`, " + "`DescriptionPayment`, " + "`PaymentData`, " + "`AmountPayment`, " + "`CreditCard`, "
			+ "`Available`) " + "VALUES (?, ?, ?, ?, ?, ?, ?);";

	private static final String SQL_UPDATE_BY_ID = "UPDATE `ipaywebapplication`.`payment` SET `DescriptionPayment`=? WHERE `Id`=?;";

	private static final String SQL_FIND_PAYMENT_BY_ID = "SELECT " + "`Id`, " + "`DatePayment`, " + "`TimePayment`, "
			+ "`DescriptionPayment`, " + "`PaymentData`, " + "`AmountPayment`, " + "`CreditCard`, " + "`Available` "
			+ "FROM `ipaywebapplication`.`payment` WHERE id = ?;";

	private static final String SQL_READ = "SELECT " + "`Id`, " + "`DatePayment`, " + "`TimePayment`, "
			+ "`DescriptionPayment`, " + "`PaymentData`, " + "`AmountPayment`, " + "`CreditCard`, " + "`Available` "
			+ "FROM `ipaywebapplication`.`payment`;";

	private static final String SQL_DELETE_PAYMENT_BY_ID = "UPDATE `ipaywebapplication`.`payment` SET `Available`= 0 WHERE `Id`=?;";

	private static final String SQL_FIND_PAYMENT_BY_CARD_ID_AND_DATE = "SELECT "
			+ "payment.Id, "
			+ "payment.DatePayment, "
			+ "payment.TimePayment, "
			+ "payment.DescriptionPayment, " 
			+ "paymentdata.PaymentDataName, "
			+ "paymentdata.PaymentDataGroup, "
			+ "paymentdata.PaymentDataDescription, "
			+ "payment.AmountPayment " 
			+ "FROM payment JOIN paymentdata ON paymentdata.id = payment.PaymentData " 
			+ "WHERE CreditCard = ? AND DatePayment BETWEEN ? AND ? ORDER BY DatePayment, TimePayment;";
	
	private static final String SQL_FIND_PAYMENT_BY_CARD_ID_AND_DATE_CHART_PIE  = "SELECT "
			+ "COUNT(*) AS Amount, "
			+ "paymentdata.PaymentDataGroup, "
			+ "TRUNCATE(SUM(payment.AmountPayment), 2) AS Sum " 
			+ "FROM payment JOIN paymentdata ON paymentdata.id = payment.PaymentData " 
			+ "WHERE CreditCard = ? AND DatePayment BETWEEN ? AND ? GROUP BY paymentdata.PaymentDataGroup;";
	
	private static final String ERROR_CREATE = "Error create payment.";
	private static final String ERROR_UPDATE_BY_ID = "Error update payment by id.";
	private static final String ERROR_FIND_ACCOUNT_BANK_BY_ID = "Error find payment by id.";
	private static final String ERROR_READ = "Error read payments.";
	private static final String ERROR_DELETE_BY_ID = "Error delete payment by id.";
	private static final String ERROR_FIND_PAYMENT_BY_CARD_AND_DATE = "Error find payments by number card and date";
	private static final String ERROR_FIND_PAYMENT_BY_CARD_AND_DATE_CHART_PIE = "Error find payments by number card and date - chart";
	
	@Override
	public Payment create(Payment entity) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
			preparedStatement.setDate(1, Date.valueOf(entity.getDatePayment()));
			preparedStatement.setTime(2, Time.valueOf(entity.getTimePayment()));
			preparedStatement.setString(3, entity.getDescriptionPayment());
			preparedStatement.setLong(4, entity.getPaymentService());
			preparedStatement.setBigDecimal(5, entity.getAmountPayment());
			preparedStatement.setLong(6, entity.getCreditCard());
			preparedStatement.setBoolean(7, true);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ERROR_CREATE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return entity;
	}

	@Override
	public Payment update(Payment entity, Long id) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
			preparedStatement.setString(1, entity.getDescriptionPayment());
			preparedStatement.setString(2, entity.getDescriptionPayment());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ERROR_UPDATE_BY_ID, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return entity;
	}

	@Override
	public Payment findById(Long id) throws DAOException {
		Payment payment = null;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PAYMENT_BY_ID)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					payment = buildPaymentRowMapper(resultSet);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_FIND_ACCOUNT_BANK_BY_ID, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return payment;
	}

	@Override
	public List<Payment> read() throws DAOException {
		List<Payment> payments = new ArrayList<>();
		Payment payment;
		Connection connection = dataBaseConnection.getConnection();
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_READ)) {
				while (resultSet.next()) {
					payment = buildPaymentRowMapper(resultSet);
					payments.add(payment);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_READ, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return payments;
	}

	@Override
	public boolean deleteById(Long id) throws DAOException {
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PAYMENT_BY_ID)) {
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
	public List<PaymentReport> findPaynemtByCardAndBetweenDate(Long cardId, LocalDate startDate, LocalDate endDate)
			throws DAOException {
		List<PaymentReport> payments = new ArrayList<>();
		PaymentReport paymentReport;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PAYMENT_BY_CARD_ID_AND_DATE)) {
			preparedStatement.setLong(1, cardId);
			preparedStatement.setDate(2, Date.valueOf(startDate.toString()));
			preparedStatement.setDate(3, Date.valueOf(endDate.toString()));
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					paymentReport = buildPaymentReportRowMapper(resultSet);
					payments.add(paymentReport);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_FIND_PAYMENT_BY_CARD_AND_DATE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return payments;
	}

	@Override
	public List<SumPaymentReportChartPie> findPaynemtByCardAndBetweenDateChartPie(Long cardId, LocalDate startDate,
			LocalDate endDate) throws DAOException {
		List<SumPaymentReportChartPie> sumPaymentReportChartPies = new ArrayList<>();
		SumPaymentReportChartPie sumPaymentReportChartPie;
		Connection connection = dataBaseConnection.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PAYMENT_BY_CARD_ID_AND_DATE_CHART_PIE)) {
			preparedStatement.setLong(1, cardId);
			preparedStatement.setDate(2, Date.valueOf(startDate.toString()));
			preparedStatement.setDate(3, Date.valueOf(endDate.toString()));
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					sumPaymentReportChartPie = buildChartPiePaymentRowMapper(resultSet);
					sumPaymentReportChartPies.add(sumPaymentReportChartPie);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_FIND_PAYMENT_BY_CARD_AND_DATE_CHART_PIE, e);
		} finally {
			dataBaseConnection.closeConnection(connection);
		}
		return sumPaymentReportChartPies;
	}

	

}
