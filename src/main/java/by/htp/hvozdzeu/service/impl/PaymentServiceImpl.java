package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.PaymentDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.service.PaymentService;

import java.time.LocalDate;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

	private PaymentDAO iPaymentDAO = DAOFactory.getPaymentDao();

	@Override
	public Payment create(Payment payment) throws DAOException {
		return iPaymentDAO.create(payment);
	}

	@Override
	public Payment findById(Long paymentId) throws DAOException {
		return iPaymentDAO.findById(paymentId);
	}

	@Override
	public List<Payment> getAllPayments() throws DAOException {
		return iPaymentDAO.read();
	}

	@Override
	public boolean deleteById(Long paymentId) throws DAOException {
		return iPaymentDAO.deleteById(paymentId);
	}

	@Override
	public List<PaymentReport> findPaymentByCardAndBetweenDate(Long cardId, LocalDate startDate, LocalDate endDate,
															   Integer countRowOnPage, Integer displacement)
			throws DAOException {
		return iPaymentDAO.findPaymentByCardAndBetweenDate(cardId, startDate, endDate, countRowOnPage, displacement);
	}

}
