package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.IUserDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDAO iUserDAO = DAOFactory.getClientDao();

    @Override
    public User create(User user) throws DAOException {
        return iUserDAO.create(user);
    }

    @Override
    public User update(User user, Long id) throws DAOException {
        return iUserDAO.update(user, id);
    }

    @Override
    public User findById(Long id) throws DAOException {
        return iUserDAO.findById(id);
    }

    @Override
    public List<User> read() throws DAOException {
        return iUserDAO.read();
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        return iUserDAO.deleteById(id);
    }

    @Override
    public User checkUser(String login, String pswd) throws DAOException {
        return iUserDAO.checkAccount(login, pswd);
    }

    @Override
    public boolean updatePassword(Long id, String password) throws DAOException {
        return iUserDAO.updatePassword(id, password);
    }

	@Override
	public List<User> pagination(Integer start, Integer count) throws DAOException {
		return iUserDAO.pagination(start, count);
	}

	@Override
	public List<User> findByParameter(String param) throws DAOException {
		return iUserDAO.findByParameter(param);
	}

    @Override
    public User findByLogin(String login) throws DAOException {
        return iUserDAO.findByLogin(login);
    }

    @Override
    public User findByRegCode(String regCode) throws DAOException {
        return iUserDAO.findByRegCode(regCode);
    }

    @Override
    public boolean unblockUser(Long userId) throws DAOException {
        return iUserDAO.unblockUser(userId);
    }

    @Override
	public List<User> listBlockedClient() throws DAOException {
		return iUserDAO.listBlockedClient();
	}
}
