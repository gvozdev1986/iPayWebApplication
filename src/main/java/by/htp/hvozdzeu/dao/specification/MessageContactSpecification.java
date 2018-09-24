package by.htp.hvozdzeu.dao.specification;

import java.util.List;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MessageContact;

/**
 * Interface with additional methods for MessageContact implements
 */
public interface MessageContactSpecification {

    List<MessageContact> unreadMessages(boolean statusUnreadMessage) throws DAOException;

    void checkMessageAsRead(Long messageContactId) throws DAOException;

    List<MessageContact> pagination(Integer start, Integer count) throws DAOException;

}
