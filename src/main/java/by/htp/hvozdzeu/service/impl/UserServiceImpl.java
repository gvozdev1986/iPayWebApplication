package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.UserDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = DAOFactory.getClientDao();

    @Override
    public User save(User user) throws DAOException {
        return userDAO.create(user);
    }

    @Override
    public User update(User user, Long userId) throws DAOException {
        return userDAO.update(user, userId);
    }

    @Override
    public User findById(Long userId) throws DAOException {
        return userDAO.findById(userId);
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        return userDAO.read();
    }

    @Override
    public boolean deleteById(Long userId) throws DAOException {
        return userDAO.deleteById(userId);
    }

    @Override
    public User checkUser(String login, String pswd) throws DAOException {
        return userDAO.checkAccount(login, pswd);
    }

    @Override
    public void updatePswd(Long userId, String password) throws DAOException {
        userDAO.updatePassword(userId, password);
    }

	@Override
	public List<User> pagination(Integer start, Integer count) throws DAOException {
		return userDAO.pagination(start, count);
	}

	@Override
	public List<User> findByParameter(String param) throws DAOException {
		return userDAO.findByParameter(param);
	}

    @Override
    public User findByLogin(String login) throws DAOException {
        return userDAO.findByLogin(login);
    }

    @Override
    public User findByRegCode(String regCode) throws DAOException {
        return userDAO.findByRegCode(regCode);
    }

    @Override
    public void unblockUser(Long userId) throws DAOException {
        userDAO.unblockUser(userId);
    }

    @Override
    public void blockUser(Long userId) throws DAOException {
        userDAO.blockUser(userId);
    }

    @Override
	public List<User> listBlockedClient() throws DAOException {
		return userDAO.listBlockedUser();
	}
}
