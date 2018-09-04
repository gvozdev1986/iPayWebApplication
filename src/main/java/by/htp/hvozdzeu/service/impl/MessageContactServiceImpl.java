package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.IMessageContactDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.IMessageContactService;

import java.util.List;

public class MessageContactServiceImpl implements IMessageContactService {

    private IMessageContactDAO iMessageContactDAO = DAOFactory.getMessageContactDAO();

    @Override
    public MessageContact create(MessageContact messageContact) throws DAOException {
        return iMessageContactDAO.create(messageContact);
    }

    @Override
    public MessageContact update(MessageContact messageContact, Long id) throws DAOException {
        return iMessageContactDAO.update(messageContact, id);
    }

    @Override
    public MessageContact findById(Long id) throws DAOException {
        return iMessageContactDAO.findById(id);
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        return iMessageContactDAO.deleteById(id);
    }

    @Override
    public List<MessageContact> read() throws DAOException {
        return iMessageContactDAO.read();
    }

	@Override
	public List<MessageContact> unreadMessages(boolean status) throws DAOException {
		return iMessageContactDAO.unreadmessages(status);
	}
}
