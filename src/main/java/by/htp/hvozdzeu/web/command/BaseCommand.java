package by.htp.hvozdzeu.web.command;

import javax.servlet.http.HttpServletRequest;

import by.htp.hvozdzeu.web.exception.CommandException;

/**
 * Interface for realize pattern Command
 */
public interface BaseCommand {

    /** Method for command
     * @param request HttpServletRequest getting parameters
     * @return String URL for forward or redirect
     * @throws CommandException Exception
     */
    String executeCommand(HttpServletRequest request) throws CommandException;
}
