package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MessageContact;

import java.util.List;

public interface MessageContactService {

    MessageContact save(MessageContact messageContact) throws DAOException;

    MessageContact findById(Long messageContactId) throws DAOException;

    List<MessageContact> getAllMessages() throws DAOException;

    List<MessageContact> unreadMessages(boolean status) throws DAOException;

    void checkMessageAsRead(Long messageContactId) throws DAOException;

    List<MessageContact> pagination(Integer start, Integer count) throws DAOException;

}
