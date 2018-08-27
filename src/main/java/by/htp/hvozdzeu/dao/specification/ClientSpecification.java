package by.htp.hvozdzeu.dao.specification;

import java.util.List;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.util.PasswordEncoder;

public interface ClientSpecification {

    RebasePassword rebasePassword = new RebasePassword();
    PasswordEncoder passwordEncoder = new PasswordEncoder();

    Client checkAccount(String login, String pswd) throws DAOException;
    Client findByLastName(String lastName) throws DAOException;
    boolean updatePassword(Long id, String password) throws DAOException;    
    boolean isOnlineUpdate(Long id) throws DAOException;
    boolean isOffLineUpdate(Long id) throws DAOException;
    List<Client> blockedClient() throws DAOException;

}
