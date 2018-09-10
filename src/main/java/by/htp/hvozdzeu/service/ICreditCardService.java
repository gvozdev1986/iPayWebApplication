package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.StatusCardReport;

import java.util.List;

public interface ICreditCardService {

    CreditCard create(CreditCard creditCard) throws DAOException;
    CreditCard update(CreditCard creditCard, Long id) throws DAOException;
    CreditCard findById(Long id) throws DAOException;
    List<CreditCard> read() throws DAOException;
    boolean deleteById(Long id) throws DAOException;
    CreditCard findByCreditCardNumber(String creditCard) throws DAOException;
    List<StatusCardReport> findCreditCardByIdClient(Long clientId) throws DAOException;
    boolean blockCreditCard(Long creditCardId) throws DAOException;
    boolean unblockCreditCard(Long creditCardId) throws DAOException;
    List<CreditCard> blockedCreditCard() throws DAOException;
    List<CreditCard> findByParameter(String param) throws DAOException;
    List<CreditCard> findBlockedByParameter(String param) throws DAOException;
    List<CreditCard> pagination(Integer start, Integer count) throws DAOException;
    Long createReturnId(CreditCard creditCard) throws DAOException;

}
