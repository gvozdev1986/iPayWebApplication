package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.BankAccountDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.service.BankAccountService;

import java.math.BigDecimal;
import java.util.List;

public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountDAO bankAccountDAO = DAOFactory.getBankAccountDao();

    @Override
    public BankAccount save(BankAccount bankAccount) throws DAOException {
        return bankAccountDAO.create(bankAccount);
    }

    @Override
    public BankAccount update(BankAccount bankAccount, Long bankAccountId) throws DAOException {
        return bankAccountDAO.update(bankAccount, bankAccountId);
    }

    @Override
    public BankAccount findById(Long bankAccountId) throws DAOException {
        return bankAccountDAO.findById(bankAccountId);
    }

    @Override
    public List<BankAccount> getAllBankAccounts() throws DAOException {
        return bankAccountDAO.read();
    }

    @Override
    public boolean deleteById(Long bankAccountId) throws DAOException {
        return bankAccountDAO.deleteById(bankAccountId);
    }

    @Override
    public boolean bankAccountBlock(Long bankAccountId) throws DAOException {
        return bankAccountDAO.bankAccountBlock(bankAccountId);
    }

    @Override
    public boolean bankAccountUnBlock(Long bankAccountId) throws DAOException {
        return bankAccountDAO.bankAccountUnBlock(bankAccountId);
    }

	@Override
	public BankAccount findByCardId(Long creditCardId) throws DAOException {
		return bankAccountDAO.findByCardId(creditCardId);
	}

	@Override
	public void updateBalance(BigDecimal newBalance, Long bankAccountId) throws DAOException {
		bankAccountDAO.updateBalance(newBalance, bankAccountId);
	}
}
