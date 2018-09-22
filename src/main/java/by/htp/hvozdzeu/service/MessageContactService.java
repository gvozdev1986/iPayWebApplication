package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MessageContact;

import java.util.List;

public interface MessageContactService {

    MessageContact create(MessageContact messageContact) throws DAOException;

    MessageContact findById(Long id) throws DAOException;

    List<MessageContact> read() throws DAOException;

    List<MessageContact> unreadMessages(boolean status) throws DAOException;

    void checkMessageAsRead(Long messageId) throws DAOException;

    List<MessageContact> pagination(Integer start, Integer count) throws DAOException;

}
