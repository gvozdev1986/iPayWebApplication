package by.htp.hvozdzeu.web.pagination;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationEvent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationEvent.class);
	public static final String NAVIGATION_BUTTON = "navigationBtn";
	public static final String NONE_EVENT = "none";
	public static final String PREVIOUS_BUTTON = "previous";
	public static final String NEXT_BUTTON = "next";	
	public static final String PAGINATION_LIST = "paginationList";

	public static Integer navigationBtnEvent(HttpServletRequest request, Integer page) {
		LOGGER.debug("Start navigationBtnEvent method");
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
	
}
