package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.User;

import java.util.List;

public interface UserService {

    User save(User user) throws DAOException;

    User update(User user, Long userId) throws DAOException;

    User findById(Long id) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    boolean deleteById(Long userId) throws DAOException;

    User checkUser(String login, String pswd) throws DAOException;

    List<User> listBlockedClient() throws DAOException;

    List<User> pagination(Integer start, Integer count) throws DAOException;

    List<User> findByParameter(String param) throws DAOException;

    User findByLogin(String login) throws DAOException;

    User findByRegCode(String regCode) throws DAOException;

    void updatePswd(Long userId, String password) throws DAOException;

    void unblockUser(Long userId) throws DAOException;

    void blockUser(Long userId) throws DAOException;

}
