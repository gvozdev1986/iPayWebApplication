package by.htp.hvozdzeu.web.command;

import javax.servlet.http.HttpServletRequest;

import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.util.PasswordEncoder;
import by.htp.hvozdzeu.web.exception.CommandException;

/**
 * Interface for realize pattern Command
 */
public interface BaseCommand { //NOSONAR

    /**
     * Instance RebasePassword for use in implementation
     */
    RebasePassword rebasePassword = RebasePassword.getInstance();

    /**
     * Instance Connection pool for use in implementation
     */
    PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

    /** Method for command
     * @param request HttpServletRequest getting parameters
     * @return String URL for forward or redirect
     * @throws CommandException Exception
     */
    String executeCommand(HttpServletRequest request) throws CommandException;
}
