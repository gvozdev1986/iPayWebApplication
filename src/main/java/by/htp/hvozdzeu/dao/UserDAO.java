package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.UserSpecification;
import by.htp.hvozdzeu.model.User;

/**
 * Interface for User entity with extends parameterized
 * interface CRUD DAO as User and specification for User.
 */
public interface IUserDAO extends ICrudDAO<User>, UserSpecification {

}
