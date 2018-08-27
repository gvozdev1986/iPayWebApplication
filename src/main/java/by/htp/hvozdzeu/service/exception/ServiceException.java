package by.htp.hvozdzeu.service.exception;

import by.htp.hvozdzeu.web.exception.CommandException;

public class ServiceException extends CommandException {

	private static final long serialVersionUID = -8444530820199529289L;

	public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
