package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.entity.Entity;

import java.util.List;

/**
 * Interface for CRUD with generic as entity
 * @param <T> generic with entity
 */
public interface CrudDAO<T extends Entity> {

    /**
     * Instance Connection pool for use in implementation
     */
    ConnectionPool dataBaseConnection = ConnectionPool.getInstance();

    /**
     * Create method with T entity
     * @param entity Entity
     * @return Entity
     * @throws DAOException custom exception
     */
    T create(T entity) throws DAOException;

    /**
     * Update method with T entity
     * @param entity Entity
     * @param id Long
     * @return Entity
     * @throws DAOException custom exception
     */
    T update(T entity, Long id) throws DAOException;

    /**
     * Find by ID method with T entity
     * @param id Long
     * @return Entity
     * @throws DAOException custom exception
     */
    T findById(Long id) throws DAOException;

    /**
     * Read method with entity
     * @return List parameterized as Entity
     * @throws DAOException custom exception
     */
    List<T> read() throws DAOException;

    /**
     * Delete by ID method
     * @param id Long
     * @return boolean
     * @throws DAOException custom exception
     */
    boolean deleteById(Long id) throws DAOException;

}
