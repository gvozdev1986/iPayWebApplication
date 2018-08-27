package by.htp.hvozdzeu.web.controller;

import by.htp.hvozdzeu.exception.UnknownCommand;
import by.htp.hvozdzeu.web.command.CommandDirector;
import by.htp.hvozdzeu.web.exception.CommandException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletController extends HttpServlet {

	private static final long serialVersionUID = -4731601876679277122L;

	private static final String COMMAND_PARAMETER = "command";
	private static final String ERROR_UNKNOWN_COMMAND = "Command cannot be null.";

	private static final Logger LOGGER = LoggerFactory.getLogger(ServletController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			LOGGER.info("Redirect to process doGet method in ServletController");
			process(req, resp);
		} catch (UnknownCommand | CommandException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			LOGGER.info("Redirect to process doPost method in ServletController");
			process(req, resp);
		} catch (UnknownCommand | CommandException e) {
			LOGGER.info(e.getMessage());
		}
	}

	private void process(HttpServletRequest req, HttpServletResponse resp)
			throws UnknownCommand, ServletException, IOException, CommandException {

		String command = req.getParameter(COMMAND_PARAMETER);
		if (command == null) {
			throw new UnknownCommand(ERROR_UNKNOWN_COMMAND);
		}

		CommandDirector commandDirector = new CommandDirector();
		String executeCommand = commandDirector.getCommand(command).execute(req, resp);

		if (executeCommand.equals("admin_panel_view")) {

			resp.sendRedirect(req.getContextPath() + "/ServletController?command=admin_panel_view");

		} else if (executeCommand.equals("client_panel_view")) {

			resp.sendRedirect(req.getContextPath() + "/ServletController?command=client_panel_view");

		}  else {

			RequestDispatcher disp = req.getRequestDispatcher(executeCommand);
			disp.forward(req, resp);

		}

	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

}
