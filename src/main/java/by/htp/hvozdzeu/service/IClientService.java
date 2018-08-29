package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;

import java.util.List;

public interface IClientService {

    Client create(Client client) throws DAOException;
    Client update(Client client, Long id) throws DAOException;
    Client findById(Long id) throws DAOException;
    List<Client> read() throws DAOException;
    boolean deleteById(Long id) throws DAOException;
    Client checkUser(String login, String pswd) throws DAOException;
    Client findByLastName(String lastName) throws DAOException;
    boolean updatePassword(Long id, String password) throws DAOException;
    boolean isOnlineUpdate(Long id) throws DAOException;
    boolean isOffLineUpdate(Long id) throws DAOException;
    List<Client> blockedClient() throws DAOException;
    List<Client> pagination(Integer start, Integer count) throws DAOException;
    List<Client> findByParameter(String param) throws DAOException;

}
