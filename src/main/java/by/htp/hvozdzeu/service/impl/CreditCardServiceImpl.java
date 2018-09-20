package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.CreditCardDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.CreditCardService;

import java.util.List;

public class CreditCardServiceImpl implements CreditCardService {

	private CreditCardDAO creditCardDAO = DAOFactory.getCreditCardDao();

	@Override
	public CreditCard create(CreditCard creditCard) throws DAOException {
		return creditCardDAO.create(creditCard);
	}

	@Override
	public CreditCard update(CreditCard creditCard, Long id) throws DAOException {
		return creditCardDAO.update(creditCard, id);
	}

	@Override
	public CreditCard findById(Long id) throws DAOException {
		return creditCardDAO.findById(id);
	}

	@Override
	public List<CreditCard> read() throws DAOException {
		return creditCardDAO.read();
	}

	@Override
	public boolean deleteById(Long id) throws DAOException {
		return creditCardDAO.deleteById(id);
	}

	@Override
	public CreditCard findByCreditCardNumber(String creditCard) throws DAOException {
		return creditCardDAO.findByCreditCardNumber(creditCard);
	}

	@Override
	public List<StatusCardReport> findCreditCardByIdClient(Long clientId) throws DAOException {
		return creditCardDAO.findCreditCardByIdClient(clientId);
	}

	@Override
	public boolean blockCreditCard(Long creditCardId) throws DAOException {
		return creditCardDAO.blockCreditCard(creditCardId);
	}

	@Override
	public boolean unblockCreditCard(Long creditCardId) throws DAOException {
		return creditCardDAO.unblockCreditCard(creditCardId);
	}

	@Override
	public List<CreditCard> blockedCreditCard() throws DAOException {
		return creditCardDAO.blockedCreditCard();
	}

	@Override
	public List<CreditCard> findByParameter(String param) throws DAOException {
		return creditCardDAO.findByParameter(param);
	}

	@Override
	public List<CreditCard> findBlockedByParameter(String param) throws DAOException {
		return creditCardDAO.findBlockedByParameter(param);
	}

	@Override
	public List<CreditCard> pagination(Integer start, Integer count) throws DAOException {
		return creditCardDAO.pagination(start, count);
	}

	@Override
	public Long createReturnId(CreditCard creditCard) throws DAOException {
		return creditCardDAO.createReturnId(creditCard);
	}
}
