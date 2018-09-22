package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountService {

    BankAccount save(BankAccount bankAccount) throws DAOException;

    BankAccount update(BankAccount bankAccount, Long bankAccountId) throws DAOException;

    BankAccount findById(Long id) throws DAOException;

    List<BankAccount> getAllBankAccounts() throws DAOException;

    boolean deleteById(Long bankAccountId) throws DAOException;

    boolean bankAccountBlock(Long bankAccountId) throws DAOException;

    boolean bankAccountUnBlock(Long bankAccountId) throws DAOException;

    BankAccount findByCardId(Long creditCardId) throws DAOException;

    void updateBalance(BigDecimal newBalance, Long bankAccountId) throws DAOException;

}
