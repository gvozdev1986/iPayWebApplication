package by.htp.hvozdzeu.web.command.impl.authorization;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.model.enums.Role;
import by.htp.hvozdzeu.service.IClientService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;
import static by.htp.hvozdzeu.web.util.RequestParameters.*;
import static by.htp.hvozdzeu.web.util.RequestAttributeEntity.*;

import java.io.IOException;

public class Authorization implements Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(Authorization.class);

	private static final String ADMIN_PANEL_COMMAND = "admin_panel_view";
	private static final String CLIENT_PANEL_COMMAND = "client_panel_view";

	private IClientService iClientService = ServiceFactory.getClientService();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		String u = request.getParameter(REQUEST_PARAM_USERNAME);
		String p = request.getParameter(REQUEST_PARAM_PSWD);
		Client client = iClientService.checkUser(u, p);	
		
		boolean available;
		
		if(client != null) {
			available = client.isAvailable();
		} else {
			request.getSession().setAttribute("messageBlock", "Error check user!");
			return LOGIN_PAGE_VIEW.getUrl();
		}

		if (!available) {
			LOGGER.info("Error auth, client has been blocked.");
			request.getSession().setAttribute("messageBlock", "Client has been blocked");
			return LOGIN_PAGE_VIEW.getUrl();
		} else {
			if (client != null && client.getRole() == Role.ADMINISTRATOR) {
				request.getSession().setAttribute(REQUEST_ATTRIBUTE_CLIENT, client);
				LOGGER.info("Redirect to admin panel");
				return ADMIN_PANEL_COMMAND;
			} else if (client != null && client.getRole() == Role.CLIENT) {
				request.getSession().setAttribute(REQUEST_ATTRIBUTE_CLIENT, client);
				LOGGER.info("Redirect to client panel");
				return CLIENT_PANEL_COMMAND;
			} else {
				LOGGER.info("Error auth, redirect to login page.");
				request.getSession().setAttribute("message", "Incorrect login or password");
				return LOGIN_PAGE_VIEW.getUrl();
			}
		}

	}
}
