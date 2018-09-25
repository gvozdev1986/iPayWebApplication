package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.UserDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.UserRowMapper;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.util.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCrudImpl extends UserRowMapper implements UserDAO {

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
            + "`RegCode` "
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
            + "`RegCode` "
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
            + "`RegCode` "
            + "FROM `ipaywebapplication`.`usr` WHERE `RegCode` = ?";

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
            + "`RegCode` "
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
            + "`RegCode` "
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
            + "`RegCode` "
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
            + "`RegCode` "
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
            + "`RegCode` "
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
            + "`RegCode` "
            + "FROM `ipaywebapplication`.`usr` "
            + "WHERE concat(usr.FirstName, usr.LastName, usr.Patronymic, "
            + "usr.PhoneHome, usr.PhoneMobile, usr.Address) like ?;";

    private static final String SQL_UNBLOCK_USER = "UPDATE `ipaywebapplication`.`usr` " +
            "SET `Available`='1' WHERE  `Id`=?;";

    private static final String SQL_BLOCK_USER = "UPDATE `ipaywebapplication`.`usr` " +
            "SET `Available`='0' WHERE  `Id`=?;";


    private static final String ERROR_SQL_CREATE_USER = "Error create user.";
    private static final String ERROR_SQL_UPDATE_USER = "Error update user.";
    private static final String ERROR_SQL_FIND_USER_BY_ID = "Error find user by id.";
    private static final String ERROR_SQL_READ_USERS = "Error getting users list.";
    private static final String ERROR_SQL_DELETE_USER_BY_ID = "Error delete user by id.";
    private static final String ERROR_SQL_CHECK_ACCOUNT = "Error check user.";
    private static final String ERROR_SQL_FIND_BY_LOGIN = "Error find by id.";
    private static final String ERROR_SQL_UPDATE_PSWD = "Error update password";
    private static final String ERROR_SQL_UNBLOCK_USER = "Error unblock user.";
    private static final String ERROR_SQL_BLOCK_USER = "Error block user.";
    private static final String ERROR_SQL_PAGINATION = "Error create pagination.";
    private static final String ERROR_SQL_FIND_USER_BY_PARAMETER = "Error find user by parameter.";
    private static final String ERROR_SQL_LIST_BLOCKED_USER = "Error getting list of users.";
    private static final String ERROR_SQL_FIND_USER_BY_REGISTRATION_CODE = "Error find user by registration code from email.";

    @Override
    public User create(User user) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            String encodedPassword = passwordEncoder.getEncodeData(rebasePassword.rebasePSWD(user.getPassword()));
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, encodedPassword);
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getPatronymic());
            preparedStatement.setDate(6, Date.valueOf(user.getDateBirth()));
            preparedStatement.setString(7, user.getPhoneHome());
            preparedStatement.setString(8, user.getPhoneMobile());
            preparedStatement.setString(9, user.getAddress());
            preparedStatement.setString(10, user.getEmail());
            preparedStatement.setBoolean(11, false);
            preparedStatement.setBoolean(12, false);
            preparedStatement.setString(13, user.getRegCode());
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_SQL_CREATE_USER);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return user;
    }

    @Override
    public User update(User user, Long userId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getPhoneHome());
            preparedStatement.setDate(5, Date.valueOf(user.getDateBirth()));
            preparedStatement.setString(6, user.getPhoneMobile());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getEmail());
            preparedStatement.setLong(9, userId);
            savepoint = connection.setSavepoint();
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_SQL_UPDATE_USER);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return user;
    }

    @Override
    public User findById(Long userId) throws DAOException {
        User user = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_FIND_USER_BY_ID);
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
            throw new DAOException(ERROR_SQL_READ_USERS);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return users;
    }

    @Override
    public boolean deleteById(Long userId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        boolean result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
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
            throw new DAOException(ERROR_SQL_DELETE_USER_BY_ID);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public User checkAccount(String login, String pswd) throws DAOException {
        User user = null;
        Connection connection = dataBaseConnection.getConnection();
        PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, passwordEncoder.getEncodeData(pswd));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = buildUserRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_CHECK_ACCOUNT);
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
            throw new DAOException(ERROR_SQL_FIND_BY_LOGIN);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return user;
    }

    @Override
    public void updatePassword(Long userId, String password) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        PasswordEncoder encoder = PasswordEncoder.getInstance();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PSWD)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, String.valueOf(
                    rebasePassword.rebasePSWD(encoder.getEncodeData(password))));
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_SQL_UPDATE_PSWD);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void unblockUser(Long userId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNBLOCK_USER)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_SQL_UNBLOCK_USER);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void blockUser(Long userId) throws DAOException {
        Connection connection = dataBaseConnection.getConnection();
        Savepoint savepoint = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_BLOCK_USER)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage());
            }
            throw new DAOException(ERROR_SQL_BLOCK_USER);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
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
            throw new DAOException(ERROR_SQL_PAGINATION);
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
            throw new DAOException(ERROR_SQL_FIND_USER_BY_PARAMETER);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return users;
    }

    @Override
    public List<User> listBlockedUser() throws DAOException {
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
            throw new DAOException(ERROR_SQL_LIST_BLOCKED_USER);
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
            throw new DAOException(ERROR_SQL_FIND_USER_BY_REGISTRATION_CODE);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return user;
    }

}
