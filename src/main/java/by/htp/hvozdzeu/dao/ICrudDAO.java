package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.entity.Entity;

import java.util.List;

public interface ICrudDAO<T extends Entity> {

    ConnectionPool dataBaseConnection = ConnectionPool.getInstance();

    T create(T entity) throws DAOException;
    T update(T entity, Long id) throws DAOException;
    T findById(Long id) throws DAOException;
    List<T> read() throws DAOException;
    boolean deleteById(Long id) throws DAOException;

}
