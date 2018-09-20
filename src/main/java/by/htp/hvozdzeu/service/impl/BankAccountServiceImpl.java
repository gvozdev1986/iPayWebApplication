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
    public BankAccount create(BankAccount bankAccount) throws DAOException {
        return bankAccountDAO.create(bankAccount);
    }

    @Override
    public BankAccount update(BankAccount bankAccount, Long id) throws DAOException {
        return bankAccountDAO.update(bankAccount, id);
    }

    @Override
    public BankAccount findById(Long id) throws DAOException {
        return bankAccountDAO.findById(id);
    }

    @Override
    public List<BankAccount> read() throws DAOException {
        return bankAccountDAO.read();
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        return bankAccountDAO.deleteById(id);
    }

    @Override
    public boolean bankAccountBlock(Long id) throws DAOException {
        return bankAccountDAO.bankAccountBlock(id);
    }

    @Override
    public boolean bankAccountUnBlock(Long id) throws DAOException {
        return bankAccountDAO.bankAccountUnBlock(id);
    }

	@Override
	public BankAccount findByCardId(Long cardId) throws DAOException {
		return bankAccountDAO.findByCardId(cardId);
	}

	@Override
	public void updateBalance(BigDecimal newBalance, Long bankAccountId) throws DAOException {
		bankAccountDAO.updateBalance(newBalance, bankAccountId);
	}
}
