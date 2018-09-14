package by.htp.hvozdzeu.web.pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class NavigationEvent {

	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationEvent.class);
	private static final String NAVIGATION_BUTTON = "navigationBtn";
	static final String PREVIOUS_BUTTON = "previous";
	static final String NEXT_BUTTON = "next";
	public static final String PAGINATION_LIST = "paginationList";

    private NavigationEvent() {
    }

    static Integer navigationBtnEvent(HttpServletRequest request, Integer page) {
		LOGGER.debug("Start navigationBtnEvent method");
		String navigationBtn;
		if (request.getParameter(NAVIGATION_BUTTON) != null) {
			navigationBtn = request.getParameter(NAVIGATION_BUTTON);
			if (navigationBtn.equals(PREVIOUS_BUTTON)) {
				page--;
				return page;
			}
			if (navigationBtn.equals(NEXT_BUTTON)) {
				page++;
				return page;
			}
		}
		return page;
	}
	
}
