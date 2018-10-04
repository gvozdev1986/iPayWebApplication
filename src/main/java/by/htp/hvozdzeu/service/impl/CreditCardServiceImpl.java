package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.CreditCardDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.CreditCardService;

import java.util.List;

public class CreditCardServiceImpl implements CreditCardService {

	private CreditCardDAO creditCardDAO = DAOFactory.getCreditCardDao();

	@Override
	public CreditCard save(CreditCard creditCard) throws DAOException {
		return creditCardDAO.create(creditCard);
	}

	@Override
	public CreditCard update(CreditCard creditCard, Long creditCardId) throws DAOException {
		return creditCardDAO.update(creditCard, creditCardId);
	}

	@Override
	public CreditCard findById(Long creditCardId) throws DAOException {
		return creditCardDAO.findById(creditCardId);
	}

	@Override
	public List<CreditCard> getAllCreditCards() throws DAOException {
		return creditCardDAO.read();
	}

	@Override
	public boolean deleteById(Long creditCardId) throws DAOException {
		return creditCardDAO.deleteById(creditCardId);
	}

	@Override
	public CreditCard findByCreditCardNumber(String creditCard) throws DAOException {
		return creditCardDAO.findByCreditCardNumber(creditCard);
	}

	@Override
	public List<CreditCard> findCreditCardByIdClient(Long userId) throws DAOException {
		return creditCardDAO.findCreditCardByIdClient(userId);
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
