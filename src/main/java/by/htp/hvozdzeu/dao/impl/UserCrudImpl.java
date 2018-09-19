package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.IUserDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.UserRowMapper;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.util.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCrudImpl extends UserRowMapper implements IUserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCrudImpl.class);

    private static final String SQL_CREATE = "INSERT INTO `ipaywebapplication`.`usr` "
            + "(`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + ")"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_BY_ID = "UPDATE `ipaywebapplication`.`usr` SET "
            + "`FirstName`=?, "
            + "`LastName`=?, "
            + "`Patronymic`=?, "
            + "`PhoneHome`=?, "
            + "`DateBirth`=?, "
            + "`PhoneMobile`=?, "
            + "`Address`=?, "
            + "`Email`=? "
            + "WHERE  `Id`=?;";

    private static final String SQL_FIND_BY_ID = "SELECT "
            + "`Id`, "
            + "`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + "FROM `ipaywebapplication`.`usr` WHERE `id` = ?";

    private static final String SQL_FIND_BY_REGISTRATION_CODE = "SELECT "
            + "`Id`, "
            + "`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + "FROM `ipaywebapplication`.`usr` WHERE `regCode` = ?";

    private static final String SQL_FIND_BY_LOGIN = "SELECT "
            + "`Id`, "
            + "`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + "FROM `ipaywebapplication`.`usr` WHERE `Login` = ?";

    private static final String CHECK_ACCOUNT = "SELECT "
            + "`Id`, "
            + "`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + "FROM `ipaywebapplication`.`usr` WHERE `Login` = ? AND `Password` = ?;";

    private static final String SQL_READ = "SELECT "
            + "`Id`, "
            + "`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + "FROM `ipaywebapplication`.`usr` WHERE `isAdmin` = false;";

    private static final String SQL_DELETE_BY_ID = "UPDATE `ipaywebapplication`.`usr` SET"
            + "`Available` = 0 WHERE `Id` = ?;";

    private static final String SQL_UPDATE_PSWD = "UPDATE `ipaywebapplication`.`usr` SET "
            + "`Password`= ? WHERE `Id`= ?;";

    private static final String SQL_LIST_BLOCKED_CLIENT = "SELECT "
            + "`Id`, "
            + "`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + "FROM `ipaywebapplication`.`usr` WHERE `usr`.`Available` = false;";

    private static final String SQL_PAGINATION = "SELECT "
            + "`Id`, "
            + "`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + "FROM `ipaywebapplication`.`usr`  WHERE `isAdmin` = false LIMIT ?, ?;";

    private static final String SQL_FIND_BY_PARAMETER = "SELECT "
            + "`Id`, "
            + "`Login`, "
            + "`Password`, "
            + "`FirstName`, "
            + "`LastName`, "
            + "`Patronymic`, "
            + "`DateBirth`, "
            + "`PhoneHome`, "
            + "`PhoneMobile`, "
            + "`Address`, "
            + "`Email`, "
            + "`Available`, "
            + "`isAdmin`, "
            + "`regCode` "
            + "FROM `ipaywebapplication`.`usr` "
            + "WHERE concat(usr.FirstName, usr.LastName, usr.Patronymic, "
            + "usr.PhoneHome, usr.PhoneMobile, usr.Address) like ?;";

    private static final String SQL_UNBLOCK_USER = "UPDATE `ipaywebapplication`.`usr` " +
            "SET `Available`='1' WHERE  `Id`=?;";

    @Override
    public User create(User entity) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            String encodedPassword = passwordEncoder.getEncodeData(rebasePassword.rebasePSWD(entity.getPassword()));
            connection.setAutoCommit(false);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, encodedPassword);
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getLastName());
            preparedStatement.setString(5, entity.getPatronymic());
            preparedStatement.setDate(6, Date.valueOf(entity.getDateBirth()));
            preparedStatement.setString(7, entity.getPhoneHome());
            preparedStatement.setString(8, entity.getPhoneMobile());
            preparedStatement.setString(9, entity.getAddress());
            preparedStatement.setString(10, entity.getEmail());
            preparedStatement.setBoolean(11, false);
            preparedStatement.setBoolean(12, false);
            preparedStatement.setString(13, entity.getRegCode());
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public User update(User entity, Long id) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getPatronymic());
            preparedStatement.setString(4, entity.getPhoneHome());
            preparedStatement.setDate(5, Date.valueOf(entity.getDateBirth()));
            preparedStatement.setString(6, entity.getPhoneMobile());
            preparedStatement.setString(7, entity.getAddress());
            preparedStatement.setString(8, entity.getEmail());
            preparedStatement.setLong(9, id);
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public User findById(Long id) throws DAOException {
        User user = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> read() throws DAOException {
        List<User> users = new ArrayList<>();
        User user;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_READ)) {
                while (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return users;
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public User checkAccount(String login, String pswd) throws DAOException {
        User user = null;
        Connection connection = dataBaseConnection.getConnection();
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, passwordEncoder.getEncodeData(pswd));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) throws DAOException {
        User user = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return user;
    }

    @Override
    public boolean updatePassword(Long id, String password) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        PasswordEncoder encoder = new PasswordEncoder();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PSWD)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, String.valueOf(
                    rebasePassword.rebasePSWD(encoder.getEncodeData(password))));
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<User> blockedUser() throws DAOException {
        List<User> users = new ArrayList<>();
        User user;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_LIST_BLOCKED_CLIENT)) {
                while (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return users;
    }

    @Override
    public boolean unblockUser(Long userId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNBLOCK_USER)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<User> pagination(Integer start, Integer count) throws DAOException {
        List<User> users = new ArrayList<>();
        User user;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_PAGINATION)) {
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, start);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return users;
    }

    @Override
    public List<User> findByParameter(String param) throws DAOException {
        List<User> users = new ArrayList<>();
        User user;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PARAMETER)) {
            preparedStatement.setString(1, "%" + param + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage()); // ERROR_FIND_BY_PARAMETER
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return users;
    }

    @Override
    public List<User> listBlockedClient() throws DAOException {
        List<User> users = new ArrayList<>();
        User user;
        Connection connection = dataBaseConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_LIST_BLOCKED_CLIENT)) {
                while (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return users;
    }

    @Override
    public User findByRegCode(String regCode) throws DAOException {
        User user = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_REGISTRATION_CODE)) {
            preparedStatement.setString(1, regCode);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return user;
    }

}
