package by.htp.hvozdzeu.dao.specification;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;

import java.util.List;

/**
 * Interface with additional methods for CreditCard implements
 */
public interface CreditCardSpecification {

    CreditCard findByCreditCardNumber(String creditCardNumber) throws DAOException;

    List<CreditCard> findCreditCardByIdClient(Long userId) throws DAOException;

    boolean blockCreditCard(Long creditCardId) throws DAOException;

    boolean unblockCreditCard(Long creditCardId) throws DAOException;

    List<CreditCard> blockedCreditCard() throws DAOException;

    List<CreditCard> findByParameter(String param) throws DAOException;

    List<CreditCard> findBlockedByParameter(String param) throws DAOException;

    List<CreditCard> pagination(Integer start, Integer count) throws DAOException;

    Long createReturnId(CreditCard creditCard) throws DAOException;
}
