package by.htp.hvozdzeu.dao.specification;

import java.util.List;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MessageContact;

public interface MessageContactSpecification {

    List<MessageContact> unreadMessages(boolean status) throws DAOException;

    void checkMessageAsRead(Long messageId) throws DAOException;

    List<MessageContact> pagination(Integer start, Integer count) throws DAOException;

}
