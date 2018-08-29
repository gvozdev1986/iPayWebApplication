package by.htp.hvozdzeu.web.command.impl.view;

import static by.htp.hvozdzeu.web.pagination.CalcilatePagination.*;
import static by.htp.hvozdzeu.web.pagination.NavigationEvent.*;
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
import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;
import static by.htp.hvozdzeu.web.pagination.WriteSessionPagination.*;

public class ListClientView implements Command {

	private static final String COUNT_BLOCKED_CLIENTS = "countBlockedClients";
	private static final String PAGINATION_NAME = "clients";
	private static final String PAGINATION_LIST_VALUE = "list_client_view";
	private IClientService iClientService = ServiceFactory.getClientService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Integer countBlockedClients = iClientService.blockedClient().size();
		Integer countRow = iClientService.read().size();

		Integer countRowOnPage = getSesstionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);
		Integer displacement = getSesstionPaginationAttribute(request, countRow, DISPLACEMENT);
		List<Client> pagination = iClientService.pagination(countRowOnPage, displacement);
		writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);

		request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
		request.getSession().setAttribute(COUNT_BLOCKED_CLIENTS, countBlockedClients);
		return LIST_CLIENT_VIEW.getUrl();
	}

}
