package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.UserSpecification;
import by.htp.hvozdzeu.model.User;

public interface IUserDAO extends ICrudDAO<User>, UserSpecification {

}
