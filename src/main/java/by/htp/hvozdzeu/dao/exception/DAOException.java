package by.htp.hvozdzeu.dao.exception;

import by.htp.hvozdzeu.service.exception.ServiceException;

public class DAOException extends ServiceException {

	private static final long serialVersionUID = 6851824836990831221L;

	public DAOException() {
        super();
    }

    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

}
