package by.htp.hvozdzeu.web.exception;

/**
 * @author Aliaksandr Hvozdzeu
 * @see by.htp.hvozdzeu.web.exception.CommandException;
 *         <code>ValidateNullRequestParamException</code>
 *         <p/>
 *         Exception class, designed specifically to
 *         describe the exceptional during data validation.
 */
public class ValidateNullRequestParamException extends CommandException {

	private static final long serialVersionUID = -8005214346109400474L;

	public ValidateNullRequestParamException() {
		super();
	}

	public ValidateNullRequestParamException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidateNullRequestParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidateNullRequestParamException(String message) {
		super(message);
	}

	public ValidateNullRequestParamException(Throwable cause) {
		super(cause);
	}

}
