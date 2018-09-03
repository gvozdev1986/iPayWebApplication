package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.IPaymentDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;
import by.htp.hvozdzeu.service.IPaymentService;

import java.time.LocalDate;
import java.util.List;

public class PaymentServiceImpl implements IPaymentService {

	private IPaymentDAO iPaymentDAO = DAOFactory.getPaymentDao();

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
	public List<PaymentReport> findPaynemtByCardAndBetweenDate(Long cardId, LocalDate startDate, LocalDate endDate, Integer start, Integer count)
			throws DAOException {
		return iPaymentDAO.findPaynemtByCardAndBetweenDate(cardId, startDate, endDate, start, count);
	}

	@Override
	public List<SumPaymentReportChartPie> findPaynemtByCardAndBetweenDateChartPie(Long cardId, LocalDate startDate,
			LocalDate endDate, Integer start, Integer count) throws DAOException {
		return iPaymentDAO.findPaynemtByCardAndBetweenDateChartPie(cardId, startDate, endDate, start, count);
	}

}
