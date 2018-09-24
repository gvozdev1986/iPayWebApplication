package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.UserSpecification;
import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.util.PasswordEncoder;

/**
 * Interface for User entity with extends parameterized
 * interface CRUD DAO as User and specification for User.
 */
public interface UserDAO extends CrudDAO<User>, UserSpecification { //NOSONAR

    /**
     * Instance RebasePassword for use in implementation
     */
    RebasePassword rebasePassword = RebasePassword.getInstance();

    /**
     * Instance Connection pool for use in implementation
     */
    PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

}
