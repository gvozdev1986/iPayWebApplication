package by.htp.hvozdzeu.dao.connection;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    private static final String DB_CONNECT_PROPERTY = "db_config";
    private static final String RESOURCE_DRIVER_NAME = "db.driver.name";
    private static final String RESOURCE_URL = "db.url";
    private static final String RESOURCE_LOGIN = "db.login";
    private static final String RESOURCE_PASS = "db.pswd";
    private static final String MAX_CONNECTION_COUNT = "db.min.connect.size";
    private static final String MIN_CONNECTION_COUNT = "db.min.connect.size";

    private static volatile ConnectionPool instance;
    private static String url;
    private static String login;
    private static String pswd;
    private static String driverName;
    private static int maxCountConnect;
    private static int minCountConnect;

    static {
        ResourceBundle rb = ResourceBundle.getBundle(DB_CONNECT_PROPERTY);
        url = rb.getString(RESOURCE_URL);
        login = rb.getString(RESOURCE_LOGIN);
        pswd = rb.getString(RESOURCE_PASS);
        driverName = rb.getString(RESOURCE_DRIVER_NAME);
        maxCountConnect = Integer.valueOf(rb.getString(MAX_CONNECTION_COUNT));
        minCountConnect = Integer.valueOf(rb.getString(MIN_CONNECTION_COUNT));
    }

    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(maxCountConnect, true);

    private ConnectionPool() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        for (int i = 0; i < minCountConnect; i++) {
            try {
                pool.add(DriverManager.getConnection(url, login, pswd));
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        LOGGER.info("Method getInstance has been created.");
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
        LOGGER.info("Method getConnection has been created.");
        Connection connection;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            LOGGER.error("Method getConnection has been interrupted.");
            Thread.currentThread().interrupt();
            throw new ConnectionException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) throws ConnectionException {
        LOGGER.info("Method closeConnection has been created.");
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error("Method closeConnection has been interrupted.");
                Thread.currentThread().interrupt();
                throw new ConnectionException(e);
            }
        }
    }

}
