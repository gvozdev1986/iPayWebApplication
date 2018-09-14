package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.IMailAccountDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.MailAccount;
import by.htp.hvozdzeu.service.IMailAccountService;

public class MailAccountServiceImpl implements IMailAccountService {

    private IMailAccountDAO iMailAccountDAO = DAOFactory.getiMailAccountDAO();

    @Override
    public void update(String mailLogin, String mailPswd) throws DAOException {
        iMailAccountDAO.update(mailLogin, mailPswd);
    }

    @Override
    public MailAccount read() throws DAOException {
        return iMailAccountDAO.read();
    }
}
