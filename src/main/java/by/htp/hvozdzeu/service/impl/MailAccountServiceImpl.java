package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.MailAccountDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.MailAccount;
import by.htp.hvozdzeu.service.MailAccountService;

public class MailAccountServiceImpl implements MailAccountService {

    private MailAccountDAO mailAccountDAO = DAOFactory.getMailAccountDAO();

    @Override
    public void update(String mailLogin, String mailPswd) throws DAOException {
        mailAccountDAO.update(mailLogin, mailPswd);
    }

    @Override
    public MailAccount getMailAccount() throws DAOException {
        return mailAccountDAO.read();
    }
}
