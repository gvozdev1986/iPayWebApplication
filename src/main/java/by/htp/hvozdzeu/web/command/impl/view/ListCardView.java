package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;
import static by.htp.hvozdzeu.web.util.RequestAttributeEntity.*;

public class ListCardView implements Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(ListCardView.class);
	private static final String PREVIOUS_BUTTON = "previous";
	private static final String NEXT_BUTTON = "next";
	private static final String COUNT_ROW_ON_PAGE = "countRowOnPage";
	private static final Integer COUNT_ROW_ON_PAGE_DEFAULT = 10;
	private static final String PAGE = "page";
	private static final String NAVIGATION_BUTTON = "navigationBtn";
	private static final String NONE_EVENT = "none";
	private static final String FIRST_PAGE_NAME_ATTRIBUTE = "firstPage";
	private static final Integer FIRST_PAGE_NAME_VALUE = 0;
	private static final String LAST_PAGE_NAME_ATTRIBUTE = "lastPage";
	private static final String COUNT_PAGE_NAME_ATTRIBUTE = "countPage";
	private static final String PAGINATION_BUTTON_ARRAY_ATTIRIBURE_NAME = "paginationBtns";
	private static final String DOTS_BTN = "...";
	private static final Integer DELTA = 2;

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Integer countRowOnPage;

		if (request.getParameter(COUNT_ROW_ON_PAGE) != null) {
			countRowOnPage = Integer.valueOf(request.getParameter(COUNT_ROW_ON_PAGE));
		} else {
			countRowOnPage = COUNT_ROW_ON_PAGE_DEFAULT;
		}

		Integer pageRowSize = countRowOnPage;
		Integer countRow = iCreditCardService.read().size();
		Integer countPage = (countRow / pageRowSize) + 1;

		Integer page;
		if (request.getParameter(PAGE) != null) {
			page = Integer.valueOf(request.getParameter(PAGE));
		} else {
			page = 0;
		}

		page = navigationBtnEvent(request, page);
		Integer displacement = page * pageRowSize;
		Integer lastPage = countPage;
		
		List<String> paginationBtns =  paginationDots(page, countPage);

		List<CreditCard> pagination = resultPagination(pageRowSize, displacement);

		request.getSession().setAttribute(PREVIOUS_BUTTON, PREVIOUS_BUTTON);
		request.getSession().setAttribute(NEXT_BUTTON, NEXT_BUTTON);
		request.getSession().setAttribute(PAGE, page);
		request.getSession().setAttribute(FIRST_PAGE_NAME_ATTRIBUTE, FIRST_PAGE_NAME_VALUE);
		request.getSession().setAttribute(LAST_PAGE_NAME_ATTRIBUTE, lastPage);
		request.getSession().setAttribute(COUNT_ROW_ON_PAGE, countRowOnPage);
		request.getSession().setAttribute(COUNT_PAGE_NAME_ATTRIBUTE, countPage);
		request.getSession().setAttribute(REQUEST_ATTRIBUTE_CREDIT_CARD, pagination);
		request.getSession().setAttribute(PAGINATION_BUTTON_ARRAY_ATTIRIBURE_NAME, paginationBtns);
		return LIST_CARD_VIEW.getUrl();
	}

	private List<String> paginationDots(int currentPage, int pageAmount) {	
		
		Integer left = currentPage - DELTA;
		Integer right = currentPage + DELTA + 1;
		List<String> range = new ArrayList<>();
		List<String> rangeWithDots = new ArrayList<>();
		Integer l = 0;

		for (int i = 1; i <= pageAmount; i++) {
			if (i == 1 || i == pageAmount || i >= left && i < right) {
				range.add("" + i);
			}
		}

		for (String i : range) {
			if (l > 0) {
				if (Integer.parseInt(i) - l == 2) {
					rangeWithDots.add("" + (l + 1));
				} else if (Integer.parseInt(i) - l != 1) {
					rangeWithDots.add(DOTS_BTN);
				}
			}
			rangeWithDots.add(i);
			l = Integer.parseInt(i);
		}

		return rangeWithDots;
	}

	private Integer navigationBtnEvent(HttpServletRequest request, Integer page) {
		String navigationBtn;
		if (request.getParameter(NAVIGATION_BUTTON) != null) {
			navigationBtn = request.getParameter(NAVIGATION_BUTTON);
			if (navigationBtn.equals(PREVIOUS_BUTTON)) {
				page--;
				LOGGER.debug(String.valueOf(page));
				return page;
			}
			if (navigationBtn.equals(NEXT_BUTTON)) {
				page++;
				LOGGER.debug(String.valueOf(page));
				return page;
			}
		} else {
			navigationBtn = NONE_EVENT;
		}
		return page;
	}

	private List<CreditCard> resultPagination(Integer pageRowSize, Integer displacement) throws DAOException {
		return iCreditCardService.pagination(pageRowSize, displacement);
	}

}
