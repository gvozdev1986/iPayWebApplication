package by.htp.hvozdzeu.web.command.impl.client;

import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.service.IClientService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;

public class FindClientByParameters implements Command {

	private IClientService iClientService = ServiceFactory.getClientService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		String param = request.getParameter("param");
		
		List<Client> creditCards = iClientService.findByParameter(param);
		
		request.getSession().setAttribute("clients", creditCards);
		return LIST_CLIENT_VIEW.getUrl();
	}

}
