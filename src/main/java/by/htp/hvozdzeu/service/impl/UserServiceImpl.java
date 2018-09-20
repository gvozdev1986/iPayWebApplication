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
    public User create(User user) throws DAOException {
        return userDAO.create(user);
    }

    @Override
    public User update(User user, Long id) throws DAOException {
        return userDAO.update(user, id);
    }

    @Override
    public User findById(Long id) throws DAOException {
        return userDAO.findById(id);
    }

    @Override
    public List<User> read() throws DAOException {
        return userDAO.read();
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        return userDAO.deleteById(id);
    }

    @Override
    public User checkUser(String login, String pswd) throws DAOException {
        return userDAO.checkAccount(login, pswd);
    }

    @Override
    public boolean updatePassword(Long id, String password) throws DAOException {
        return userDAO.updatePassword(id, password);
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
    public boolean unblockUser(Long userId) throws DAOException {
        return userDAO.unblockUser(userId);
    }

    @Override
    public boolean blockUser(Long userId) throws DAOException {
        return userDAO.blockUser(userId);
    }

    @Override
	public List<User> listBlockedClient() throws DAOException {
		return userDAO.listBlockedClient();
	}
}
