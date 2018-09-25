package by.htp.hvozdzeu.dao.specification;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.User;

import java.util.List;

/**
 * Interface with additional methods for User implements
 */
public interface UserSpecification {

    User checkAccount(String login, String pswd) throws DAOException;

    User findByLogin(String login) throws DAOException;

    void updatePassword(Long userId, String password) throws DAOException;

    void unblockUser(Long userId) throws DAOException;

    void blockUser(Long userId) throws DAOException;

    List<User> pagination(Integer start, Integer count) throws DAOException;

    List<User> findByParameter(String param) throws DAOException;

    List<User> listBlockedUser() throws DAOException;

    User findByRegCode(String regCode) throws DAOException;

}
