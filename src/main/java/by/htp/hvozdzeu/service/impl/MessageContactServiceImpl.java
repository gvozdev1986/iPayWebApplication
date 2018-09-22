package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.MessageContactDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.MessageContactService;

import java.util.List;

public class MessageContactServiceImpl implements MessageContactService {

    private MessageContactDAO iMessageContactDAO = DAOFactory.getMessageContactDAO();

    @Override
    public MessageContact save(MessageContact messageContact) throws DAOException {
        return iMessageContactDAO.create(messageContact);
    }

    @Override
    public MessageContact findById(Long messageContactId) throws DAOException {
        return iMessageContactDAO.findById(messageContactId);
    }

    @Override
    public List<MessageContact> getAllMessages() throws DAOException {
        return iMessageContactDAO.read();
    }

	@Override
	public List<MessageContact> unreadMessages(boolean status) throws DAOException {
		return iMessageContactDAO.unreadMessages(status);
	}

    @Override
    public void checkMessageAsRead(Long messageContactId) throws DAOException {
        iMessageContactDAO.checkMessageAsRead(messageContactId);
    }

    @Override
    public List<MessageContact> pagination(Integer start, Integer count) throws DAOException {
        return iMessageContactDAO.pagination(start, count);
    }
}
