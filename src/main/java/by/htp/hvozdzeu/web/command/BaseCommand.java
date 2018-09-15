package by.htp.hvozdzeu.web.command;

import javax.servlet.http.HttpServletRequest;

import by.htp.hvozdzeu.web.exception.CommandException;

public interface BaseCommand {
	 String executeCommand (HttpServletRequest request) throws CommandException;	 
}
