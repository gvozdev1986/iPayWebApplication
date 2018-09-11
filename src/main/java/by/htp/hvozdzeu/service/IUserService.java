package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.User;

import java.util.List;

public interface IUserService {

	User create(User client) throws DAOException;

	User update(User client, Long id) throws DAOException;

	User findById(Long id) throws DAOException;

	List<User> read() throws DAOException;

	boolean deleteById(Long id) throws DAOException;

	User checkUser(String login, String pswd) throws DAOException;

	boolean updatePassword(Long id, String password) throws DAOException;

	List<User> listBlockedClient() throws DAOException;

	List<User> pagination(Integer start, Integer count) throws DAOException;

	List<User> findByParameter(String param) throws DAOException;

	User findByLogin(String login) throws DAOException;

    User findByRegCode(String regCode) throws DAOException;

    boolean unblockUser(Long userId) throws DAOException;

}
