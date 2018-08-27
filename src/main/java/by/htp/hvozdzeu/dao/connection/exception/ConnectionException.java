package by.htp.hvozdzeu.dao.connection.exception;

import by.htp.hvozdzeu.dao.exception.DAOException;

public class ConnectionException extends DAOException {

	private static final long serialVersionUID = 1703971928147603734L;

	public ConnectionException() {
    }

    public ConnectionException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}