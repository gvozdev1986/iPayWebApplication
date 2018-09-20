package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.MessageContactSpecification;
import by.htp.hvozdzeu.model.MessageContact;

/**
 * Interface for MessageContact entity with extends parameterized
 * interface CRUD DAO as MessageContact and specification for MessageContact.
 */
public interface MessageContactDAO extends CrudDAO<MessageContact>, MessageContactSpecification {
}
