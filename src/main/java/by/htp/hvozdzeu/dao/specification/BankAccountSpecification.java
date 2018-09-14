package by.htp.hvozdzeu.dao.specification;

import java.math.BigDecimal;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.BankAccount;

public interface BankAccountSpecification {

    boolean bankAccountBlock(Long id) throws DAOException;

    boolean bankAccountUnBlock(Long id) throws DAOException;

    BankAccount findByCardId(Long cardId) throws DAOException;

    void updateBalance(BigDecimal newBalance, Long bankAccountId) throws DAOException;

}
