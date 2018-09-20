package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.PaymentDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;
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
	public Payment update(Payment payment, Long id) throws DAOException {
		return iPaymentDAO.update(payment, id);
	}

	@Override
	public Payment findById(Long id) throws DAOException {
		return iPaymentDAO.findById(id);
	}

	@Override
	public List<Payment> read() throws DAOException {
		return iPaymentDAO.read();
	}

	@Override
	public boolean deleteById(Long id) throws DAOException {
		return iPaymentDAO.deleteById(id);
	}

	@Override
	public List<PaymentReport> findPaymentByCardAndBetweenDate(Long cardId, LocalDate startDate, LocalDate endDate,
															   Integer countRowOnPage, Integer displacement)
			throws DAOException {
		return iPaymentDAO.findPaymentByCardAndBetweenDate(cardId, startDate, endDate, countRowOnPage, displacement);
	}

	@Override
	public List<SumPaymentReportChartPie> findPaymentByCardAndBetweenDateChartPie(Long cardId, LocalDate startDate,
																				  LocalDate endDate) throws DAOException {
		return iPaymentDAO.findPaymentByCardAndBetweenDateChartPie(cardId, startDate, endDate);
	}

}
