package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.PaymentDataDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.service.PaymentDataService;

import java.util.List;

public class PaymentDataServiceImpl implements PaymentDataService {

	private PaymentDataDAO iPaymentDataDAO = DAOFactory.getPaymentServiceDao();

	@Override
	public PaymentData save(PaymentData paymentData) throws DAOException {
		return iPaymentDataDAO.create(paymentData);
	}

	@Override
	public PaymentData update(PaymentData paymentData, Long paymentDataId) throws DAOException {
		return iPaymentDataDAO.update(paymentData, paymentDataId);
	}

	@Override
	public PaymentData findById(Long paymentDataId) throws DAOException {
		return iPaymentDataDAO.findById(paymentDataId);
	}

	@Override
	public List<PaymentData> getAllPaymentsData() throws DAOException {
		return iPaymentDataDAO.read();
	}

	@Override
	public boolean deleteById(Long paymentDataId) throws DAOException {
		return iPaymentDataDAO.deleteById(paymentDataId);
	}

	@Override
	public List<PaymentData> pagination(Integer start, Integer count) throws DAOException {
		return iPaymentDataDAO.pagination(start, count);
	}

	@Override
	public List<PaymentData> findByParameter(String param) throws DAOException {
		return iPaymentDataDAO.findByParameter(param);
	}

	@Override
	public Long maxIndex() throws DAOException {
		return iPaymentDataDAO.maxIndex();
	}

}
