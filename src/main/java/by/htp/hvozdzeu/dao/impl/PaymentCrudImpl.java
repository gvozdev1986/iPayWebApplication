package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.PaymentDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.PaymentRowMapper;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.report.PaymentReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentCrudImpl extends PaymentRowMapper implements PaymentDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentCrudImpl.class);

    private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`payment` "
            + "(`DatePayment`, "
            + "`TimePayment`, "
            + "`DescriptionPayment`, "
            + "`PaymentData`, "
            + "`AmountPayment`, "
            + "`CreditCard`, "
            + "`Available`, "
            + "`OrderNo`) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_FIND_PAYMENT_BY_ID = "SELECT "
            + "`Id`, "
            + "`DatePayment`, "
            + "`TimePayment`, "
            + "`DescriptionPayment`, "
            + "`PaymentData`, "
            + "`AmountPayment`, "
            + "`CreditCard`, "
            + "`Available`, "
            + "`OrderNo` "
            + "FROM `ipaywebapplication`.`payment` WHERE id = ?;";

    private static final String SQL_READ = "SELECT "
            + "`Id`, "
            + "`DatePayment`, "
            + "`TimePayment`, "
            + "`DescriptionPayment`, "
            + "`PaymentData`, "
            + "`AmountPayment`, "
            + "`CreditCard`, "
            + "`Available`, "
            + "`OrderNo` "
            + "FROM `ipaywebapplication`.`payment`;";

    private static final String SQL_DELETE_PAYMENT_BY_ID = "UPDATE " +
            "`ipaywebapplication`.`payment` SET `Available`= 0 WHERE `Id`=?;";

    private static final String SQL_FIND_PAYMENT_BY_CARD_ID_AND_DATE = "SELECT "
            + "payment.Id, "
            + "payment.DatePayment, "
            + "payment.TimePayment, "
            + "payment.DescriptionPayment, "
            + "paymentdata.PaymentDataName, "
            + "paymentdata.PaymentDataGroup, "
            + "paymentdata.PaymentDataDescription, "
            + "payment.AmountPayment, "
            + "payment.OrderNo "
            + "FROM payment JOIN paymentdata ON paymentdata.id = payment.PaymentData "
            + "WHERE CreditCard = ? AND DatePayment BETWEEN ? AND ? ORDER BY DatePayment, TimePayment LIMIT ?,?;";

    @Override
    public Payment create(Payment payment) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            connection.setAutoCommit(false);
            preparedStatement.setDate(1, Date.valueOf(payment.getDatePayment()));
            preparedStatement.setTime(2, Time.valueOf(payment.getTimePayment()));
            preparedStatement.setString(3, payment.getDescriptionPayment());
            preparedStatement.setLong(4, payment.getPaymentService());
            preparedStatement.setBigDecimal(5, payment.getAmountPayment());
            preparedStatement.setLong(6, payment.getCreditCard());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, payment.getOrderNo());
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
        return payment;
    }

    @Override
    public Payment update(Payment payment, Long paymentId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Payment findById(Long paymentId) throws DAOException {
        Payment payment = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PAYMENT_BY_ID)) {
            preparedStatement.setLong(1, paymentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    payment = buildPaymentRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
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
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return payments;
    }

    @Override
    public boolean deleteById(Long paymentId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PAYMENT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, paymentId);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
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
        return result;
    }

    @Override
    public List<PaymentReport> findPaymentByCardAndBetweenDate(Long creditCardId, LocalDate startDate, LocalDate endDate,
                                                               Integer countRowOnPage, Integer displacement)
            throws DAOException {
        List<PaymentReport> payments = new ArrayList<>();
        PaymentReport paymentReport;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PAYMENT_BY_CARD_ID_AND_DATE)) {
            preparedStatement.setLong(1, creditCardId);
            preparedStatement.setDate(2, Date.valueOf(startDate.toString()));
            preparedStatement.setDate(3, Date.valueOf(endDate.toString()));
            preparedStatement.setInt(4, displacement);
            preparedStatement.setInt(5, countRowOnPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    paymentReport = buildPaymentReportRowMapper(resultSet);
                    payments.add(paymentReport);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return payments;
    }
}
