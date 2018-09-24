package by.htp.hvozdzeu.dao.specification;

import java.math.BigDecimal;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.BankAccount;

/**
 * Interface with additional methods for BankAccount implements
 */
public interface BankAccountSpecification {

    boolean bankAccountBlock(Long bankAccountId) throws DAOException;

    boolean bankAccountUnBlock(Long bankAccountId) throws DAOException;

    BankAccount findByCardId(Long creditCardId) throws DAOException;

    void updateBalance(BigDecimal newBalance, Long bankAccountId) throws DAOException;

}
