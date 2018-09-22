package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.CreditCardService;
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

public class ListCardViewCommandImpl implements BaseCommand {

	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
	private static final String PAGINATION_NAME = "creditCard";
	private static final String PAGINATION_LIST_VALUE = "list_card_view";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Integer countRow = creditCardService.getAllCreditCards().size();

		Integer countRowOnPage = getSessionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);
		Integer displacement = getSessionPaginationAttribute(request, countRow, DISPLACEMENT);
		List<CreditCard> pagination = creditCardService.pagination(countRowOnPage, displacement);
		writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);

		request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
		return PagePathConstantPool.LIST_CARD_VIEW;
	}

}
