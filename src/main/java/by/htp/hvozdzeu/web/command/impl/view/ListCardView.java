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
import static by.htp.hvozdzeu.web.util.RequestAttributeEntity.*;

public class ListCardView implements Command {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Integer countRowOnPage;

		if (request.getParameter("countRowOnPage") != null) {
			countRowOnPage = Integer.valueOf(request.getParameter("countRowOnPage"));
		} else {
			countRowOnPage = 10;
		}

		Integer pageRowSize = countRowOnPage;

		Integer countRow = iCreditCardService.read().size();
		Integer countPage = countRow / pageRowSize;

		Integer page;
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
		} else {
			page = 0;
		}

		String navigationBtn;
		if (request.getParameter("navigationBtn") != null) {
			navigationBtn = request.getParameter("navigationBtn");

			if (navigationBtn.equals("previous")) {
				System.out.println(navigationBtn);
				page--;
			}

			if (navigationBtn.equals("next")) {
				System.out.println(navigationBtn);
				page++;
			}
		} else {
			navigationBtn = "none";
		}

		Integer displacement = page * pageRowSize;

		Integer firstPage = 0;
		Integer lastPage = countPage - 1;

		List<CreditCard> pagination = iCreditCardService.pagination(pageRowSize, displacement);

		request.getSession().setAttribute("previous", "previous");
		request.getSession().setAttribute("next", "next");
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("firstPage", firstPage);
		request.getSession().setAttribute("lastPage", lastPage);
		request.getSession().setAttribute("countRowOnPage", countRowOnPage);
		request.getSession().setAttribute("countPage", countPage);
		request.getSession().setAttribute(REQUEST_ATTRIBUTE_CREDIT_CARD, pagination);
		return LIST_CARD_VIEW.getUrl();
	}

}
