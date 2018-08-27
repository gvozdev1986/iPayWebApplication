package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.IBankAccountDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.service.IBankAccountService;

import java.math.BigDecimal;
import java.util.List;

public class BankAccountServiceImpl implements IBankAccountService {

    private IBankAccountDAO iBankAccountDAO = DAOFactory.getBankAccountDao();

    @Override
    public BankAccount create(BankAccount bankAccount) throws DAOException {
        return iBankAccountDAO.create(bankAccount);
    }

    @Override
    public BankAccount update(BankAccount bankAccount, Long id) throws DAOException {
        return iBankAccountDAO.update(bankAccount, id);
    }

    @Override
    public BankAccount findById(Long id) throws DAOException {
        return iBankAccountDAO.findById(id);
    }

    @Override
    public List<BankAccount> read() throws DAOException {
        return iBankAccountDAO.read();
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        return iBankAccountDAO.deleteById(id);
    }

    @Override
    public boolean bankAccountBlock(Long id) throws DAOException {
        return iBankAccountDAO.bankAccountBlock(id);
    }

    @Override
    public boolean bankAccountUnBlock(Long id) throws DAOException {
        return iBankAccountDAO.bankAccountUnBlock(id);
    }

	@Override
	public BankAccount findByCardId(Long cardId) throws DAOException {
		return iBankAccountDAO.findByCardId(cardId);
	}

	@Override
	public void updateBalance(BigDecimal newBalance, Long bankAccountId) throws DAOException {
		iBankAccountDAO.updateBalance(newBalance, bankAccountId);
	}
}
