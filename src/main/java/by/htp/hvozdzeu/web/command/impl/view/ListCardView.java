package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;
import static by.htp.hvozdzeu.web.pagination.WriteSessionPagination.getSesstionPaginationAttribute;
import static by.htp.hvozdzeu.web.pagination.WriteSessionPagination.writeSessionPagination;
import static by.htp.hvozdzeu.web.pagination.NavigationEvent.*;
import static by.htp.hvozdzeu.web.pagination.CalcilatePagination.*;

public class ListCardView implements Command {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private static final String PAGINATION_NAME = "creditCard";
	private static final String PAGINATION_LIST_VALUE = "list_card_view";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Integer countRow = iCreditCardService.read().size();

		Integer countRowOnPage = getSesstionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);
		Integer displacement = getSesstionPaginationAttribute(request, countRow, DISPLACEMENT);
		List<CreditCard> pagination = iCreditCardService.pagination(countRowOnPage, displacement);
		writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);

		request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
		return LIST_CARD_VIEW.getUrl();
	}

}
