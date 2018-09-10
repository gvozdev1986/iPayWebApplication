package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.ICreditCardDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.ICreditCardService;

import java.util.List;

public class CreditCardServiceImpl implements ICreditCardService {

	private ICreditCardDAO iCreditCardDAO = DAOFactory.getCreditCardDao();

	@Override
	public CreditCard create(CreditCard creditCard) throws DAOException {
		return iCreditCardDAO.create(creditCard);
	}

	@Override
	public CreditCard update(CreditCard creditCard, Long id) throws DAOException {
		return iCreditCardDAO.update(creditCard, id);
	}

	@Override
	public CreditCard findById(Long id) throws DAOException {
		return iCreditCardDAO.findById(id);
	}

	@Override
	public List<CreditCard> read() throws DAOException {
		return iCreditCardDAO.read();
	}

	@Override
	public boolean deleteById(Long id) throws DAOException {
		return iCreditCardDAO.deleteById(id);
	}

	@Override
	public CreditCard findByCreditCardNumber(String creditCard) throws DAOException {
		return iCreditCardDAO.findByCreditCardNumber(creditCard);
	}

	@Override
	public List<StatusCardReport> findCreditCardByIdClient(Long clientId) throws DAOException {
		return iCreditCardDAO.findCreditCardByIdClient(clientId);
	}

	@Override
	public boolean blockCreditCard(Long creditCardId) throws DAOException {
		return iCreditCardDAO.blockCreditCard(creditCardId);
	}

	@Override
	public boolean unblockCreditCard(Long creditCardId) throws DAOException {
		return iCreditCardDAO.unblockCreditCard(creditCardId);
	}

	@Override
	public List<CreditCard> blockedCreditCard() throws DAOException {
		return iCreditCardDAO.blockedCreditCard();
	}

	@Override
	public List<CreditCard> findByParameter(String param) throws DAOException {
		return iCreditCardDAO.findByParameter(param);
	}

	@Override
	public List<CreditCard> findBlockedByParameter(String param) throws DAOException {
		return iCreditCardDAO.findBlockedByParameter(param);
	}

	@Override
	public List<CreditCard> pagination(Integer start, Integer count) throws DAOException {
		return iCreditCardDAO.pagination(start, count);
	}

	@Override
	public Long createReturnId(CreditCard creditCard) throws DAOException {
		return iCreditCardDAO.createReturnId(creditCard);
	}
}
