package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MailAccount;

public interface IMailAccountService {

    void update(String mailLogin, String mailPswd) throws DAOException;

    MailAccount read() throws DAOException;

}
