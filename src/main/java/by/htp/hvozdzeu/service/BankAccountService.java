package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountService {

    BankAccount create(BankAccount bankAccount) throws DAOException;

    BankAccount update(BankAccount bankAccount, Long id) throws DAOException;

    BankAccount findById(Long id) throws DAOException;

    List<BankAccount> read() throws DAOException;

    boolean deleteById(Long id) throws DAOException;

    boolean bankAccountBlock(Long id) throws DAOException;

    boolean bankAccountUnBlock(Long id) throws DAOException;

    BankAccount findByCardId(Long cardId) throws DAOException;

    void updateBalance(BigDecimal newBalance, Long bankAccountId) throws DAOException;

}
