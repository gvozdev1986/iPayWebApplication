package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MailAccount;

public interface MailAccountService {

    void update(String mailLogin, String mailPswd) throws DAOException;

    MailAccount getMailAccount() throws DAOException;

}
