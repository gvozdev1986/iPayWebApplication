package by.htp.hvozdzeu.exception;

public class UnknownCommand extends Exception {

    private static final long serialVersionUID = -4985930044940573757L;

    public UnknownCommand() {
        super();
    }

    public UnknownCommand(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnknownCommand(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownCommand(String message) {
        super(message);
    }

    public UnknownCommand(Throwable cause) {
        super(cause);
    }


}
