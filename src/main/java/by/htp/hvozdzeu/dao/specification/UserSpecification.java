package by.htp.hvozdzeu.dao.specification;

import java.util.List;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.util.PasswordEncoder;

public interface UserSpecification {

    RebasePassword rebasePassword = new RebasePassword();
    PasswordEncoder passwordEncoder = new PasswordEncoder();

    User checkAccount(String login, String pswd) throws DAOException;

    User findByLogin(String login) throws DAOException;

    boolean updatePassword(Long id, String password) throws DAOException;

    List<User> blockedUser() throws DAOException;

    boolean unblockUser(Long userId) throws DAOException;

    boolean blockUser(Long userId) throws DAOException;

    List<User> pagination(Integer start, Integer count) throws DAOException;

    List<User> findByParameter(String param) throws DAOException;

    List<User> listBlockedClient() throws DAOException;

    User findByRegCode(String regCode) throws DAOException;

}
