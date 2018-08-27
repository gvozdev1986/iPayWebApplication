package by.htp.hvozdzeu.dao.connection;

import by.htp.hvozdzeu.web.controller.ServletController;
import by.htp.hvozdzeu.dao.connection.exception.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletController.class);

    private static final String DB_CONNECT_PROPERTY = "db_config";
    private static final String RESOURCE_DRIVER_NAME = "db.driver.name";
    private static final String RESOURCE_URL = "db.url";
    private static final String RESOURCE_LOGIN = "db.login";
    private static final String RESOURCE_PASS = "db.pass";
    private static final int MAX_CONNECTION_COUNT = 80;
    private static final int MIN_CONNECTION_COUNT = 5;

    private static volatile ConnectionPool instance;
    private static String url;
    private static String login;
    private static String pass;
    private static String driverName;

    static {
        ResourceBundle rb = ResourceBundle.getBundle(DB_CONNECT_PROPERTY);
        url = rb.getString(RESOURCE_URL);
        login = rb.getString(RESOURCE_LOGIN);
        pass = rb.getString(RESOURCE_PASS);
        driverName = rb.getString(RESOURCE_DRIVER_NAME);
    }

    private Integer currentConnectionNumber = MIN_CONNECTION_COUNT;
    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT, true);

    private ConnectionPool() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        for (int i = 0; i < MIN_CONNECTION_COUNT; i++) {
            try {
                pool.add(DriverManager.getConnection(url, login, pass));
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws ConnectionException {

        Connection connection;
        try {
            if (pool.isEmpty() && currentConnectionNumber < MAX_CONNECTION_COUNT) {
                openAdditionalConnection();
            }
            connection = pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) throws ConnectionException {
        if (connection != null) {

            if (currentConnectionNumber > MIN_CONNECTION_COUNT) {
                currentConnectionNumber--;
            }
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ConnectionException(e);
            }

        }
    }

    private void openAdditionalConnection() throws ConnectionException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new ConnectionException(e);
        }
        try {
            pool.add(DriverManager.getConnection(url, login, pass));
            currentConnectionNumber++;
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }


}
