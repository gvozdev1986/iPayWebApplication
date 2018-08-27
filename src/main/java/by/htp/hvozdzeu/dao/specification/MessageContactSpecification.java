package by.htp.hvozdzeu.dao.specification;

import java.util.List;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MessageContact;

public interface MessageContactSpecification {

	List<MessageContact> unreadmessages(boolean status) throws DAOException;
	
}
