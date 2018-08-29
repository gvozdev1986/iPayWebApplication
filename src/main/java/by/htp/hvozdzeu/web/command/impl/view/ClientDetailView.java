package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.service.IClientService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;

public class ClientDetailView implements Command{

	private IClientService iClientService = ServiceFactory.getClientService();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Long clientId = Long.valueOf(request.getParameter("clientId"));		
		Client client = iClientService.findById(clientId);
		
		request.getSession().setAttribute("clientDetail", client);
		return CLIENT_DETAIL_VIEW.getUrl();
	}

}
