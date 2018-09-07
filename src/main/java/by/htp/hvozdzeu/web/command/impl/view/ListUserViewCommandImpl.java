package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.htp.hvozdzeu.web.pagination.CalculatePagination.COUNT_ROW_ON_PAGE;
import static by.htp.hvozdzeu.web.pagination.CalculatePagination.DISPLACEMENT;
import static by.htp.hvozdzeu.web.pagination.NavigationEvent.PAGINATION_LIST;
import static by.htp.hvozdzeu.web.pagination.WriteSessionPagination.getSessionPaginationAttribute;
import static by.htp.hvozdzeu.web.pagination.WriteSessionPagination.writeSessionPagination;


public class ListUserViewCommandImpl implements BaseCommand {

	private static final String COUNT_BLOCKED_CLIENTS = "countBlockedClients";
	private static final String PAGINATION_NAME = "clients";
	private static final String PAGINATION_LIST_VALUE = "list_client_view";
	private IUserService iClientService = ServiceFactory.getUserService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Integer countBlockedClients = iClientService.listBlockedClient().size();
		Integer countRow = iClientService.read().size();

		Integer countRowOnPage = getSessionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);
		Integer displacement = getSessionPaginationAttribute(request, countRow, DISPLACEMENT);
		List<User> pagination = iClientService.pagination(countRowOnPage, displacement);
		writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);

		request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
		request.getSession().setAttribute(COUNT_BLOCKED_CLIENTS, countBlockedClients);
		return PagePathConstantPool.LIST_CLIENT_VIEW;
	}

}
