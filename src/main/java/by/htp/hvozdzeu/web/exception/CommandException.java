package by.htp.hvozdzeu.web.exception;

/**
 * @author Aliaksandr Hvozdzeu
 * @see java.lang.Exception;
 *         <code>CommandException</code>
 *         <p/>
 *         Exception class created specifically to describe the exceptional
 *         situation arises in the web layer application.
 */
public class CommandException extends Exception{

	private static final long serialVersionUID = -498519885940573757L;

	public CommandException() {
		super();
	}

	public CommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(Throwable cause) {
		super(cause);
	}

}