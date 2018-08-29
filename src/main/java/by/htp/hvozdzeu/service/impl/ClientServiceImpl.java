package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.IClientDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.service.IClientService;

import java.util.List;

public class ClientServiceImpl implements IClientService {

    private IClientDAO iClientDAO = DAOFactory.getClientDao();

    @Override
    public Client create(Client client) throws DAOException {
        return iClientDAO.create(client);
    }

    @Override
    public Client update(Client client, Long id) throws DAOException {
        return iClientDAO.update(client, id);
    }

    @Override
    public Client findById(Long id) throws DAOException {
        return iClientDAO.findById(id);
    }

    @Override
    public List<Client> read() throws DAOException {
        return iClientDAO.read();
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        return iClientDAO.deleteById(id);
    }

    @Override
    public Client checkUser(String login, String pswd) throws DAOException {
        return iClientDAO.checkAccount(login, pswd);
    }

    @Override
    public Client findByLastName(String lastName) throws DAOException {
        return iClientDAO.findByLastName(lastName);
    }

    @Override
    public boolean updatePassword(Long id, String password) throws DAOException {
        return iClientDAO.updatePassword(id, password);
    }

	@Override
	public boolean isOnlineUpdate(Long id) throws DAOException {		
		return iClientDAO.isOnlineUpdate(id);
	}

	@Override
	public boolean isOffLineUpdate(Long id) throws DAOException {
		return iClientDAO.isOffLineUpdate(id);
	}

	@Override
	public List<Client> blockedClient() throws DAOException {
		return iClientDAO.blockedClient();
	}

	@Override
	public List<Client> pagination(Integer start, Integer count) throws DAOException {
		return iClientDAO.pagination(start, count);
	}

	@Override
	public List<Client> findByParameter(String param) throws DAOException {
		return iClientDAO.findByParameter(param);
	}
}
