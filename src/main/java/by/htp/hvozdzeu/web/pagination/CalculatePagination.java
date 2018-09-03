package by.htp.hvozdzeu.web.pagination;

import static by.htp.hvozdzeu.web.pagination.NavigationEvent.navigationBtnEvent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatePagination {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatePagination.class);
	public static final String COUNT_ROW_ON_PAGE = "countRowOnPage";
	private static final Integer COUNT_ROW_ON_PAGE_DEFAULT = 10;
	public static final String PAGE = "page";
	public static final String COUNT_PAGE = "countPage";
	private static final Integer DEFAULT_PAGE = 0;
	public static final String DISPLACEMENT = "displacement";
	public static final String LAST_PAGE = "lastPage";
	public static final Integer FIRST_PAGE_NAME_VALUE = 0;
	public static final String FIRST_PAGE = "firstPage";
	public static final String PAGINATION_BUTTON_ARRAY_ATTRIBUTE_NAME = "paginationBtns";
	
	public static Map<String, Integer> calculatPagination(HttpServletRequest request, Integer countRow){
		Map<String, Integer> calc = new HashMap<>();
		calc.clear();
		
		Integer countRowOnPage;

		if (request.getParameter(COUNT_ROW_ON_PAGE) != null) {
			countRowOnPage = Integer.valueOf(request.getParameter(COUNT_ROW_ON_PAGE));
		} else {
			countRowOnPage = COUNT_ROW_ON_PAGE_DEFAULT;
		}

		Integer countPage = (countRow / countRowOnPage) + 1;

		Integer page;
		if (request.getParameter(PAGE) != null) {
			page = Integer.valueOf(request.getParameter(PAGE));
		} else {
			page = DEFAULT_PAGE;
		}

		page = navigationBtnEvent(request, page);
		Integer displacement = page * countRowOnPage;
		Integer lastPage = countPage;
		
		calc.put(PAGE, page);
		calc.put(COUNT_PAGE, countPage);		
		calc.put(COUNT_ROW_ON_PAGE, countRowOnPage);		
		calc.put(DISPLACEMENT, displacement);
		calc.put(LAST_PAGE, lastPage);
		
		LOGGER.debug("Get paggination data: PAGE-{} COUNT_PAGE-{} COUNT_ROW_ON_PAGE-{} DISPLACEMENT-{} LAST_PAGE-{}", page, countPage, countRowOnPage, displacement,lastPage);
		
		return calc;
	}
	
}
