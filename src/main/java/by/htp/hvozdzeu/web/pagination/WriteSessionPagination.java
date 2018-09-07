package by.htp.hvozdzeu.web.pagination;

import static by.htp.hvozdzeu.web.pagination.CalculatePagination.*;
import static by.htp.hvozdzeu.web.pagination.CalculatePagination.calculatePagination;
import static by.htp.hvozdzeu.web.pagination.NavigationEvent.*;
import static by.htp.hvozdzeu.web.pagination.PaginationDots.paginationDots;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WriteSessionPagination {

    private WriteSessionPagination() {
    }

    public static Integer getSessionPaginationAttribute(HttpServletRequest request, Integer countRow, String nameAttribute) {
        Map<String, Integer> calculatePagination = calculatePagination(request, countRow);
        return calculatePagination.get(nameAttribute);
    }

    public static void writeSessionPagination(HttpServletRequest request, Integer countRow, String paginationName, List<?> pagination) {

        Map<String, Integer> calculatePagination = calculatePagination(request, countRow);
        Integer page = calculatePagination.get(PAGE);
        Integer countPage = calculatePagination.get(COUNT_PAGE);
        Integer countRowOnPage = calculatePagination.get(COUNT_ROW_ON_PAGE);
        Integer lastPage = calculatePagination.get(LAST_PAGE);

        List<String> paginationBtn = paginationDots(page, countPage);
        request.getSession().setAttribute(PREVIOUS_BUTTON, PREVIOUS_BUTTON);
        request.getSession().setAttribute(NEXT_BUTTON, NEXT_BUTTON);
        request.getSession().setAttribute(PAGE, page);
        request.getSession().setAttribute(FIRST_PAGE, FIRST_PAGE_NAME_VALUE);
        request.getSession().setAttribute(LAST_PAGE, lastPage);
        request.getSession().setAttribute(COUNT_ROW_ON_PAGE, countRowOnPage);
        request.getSession().setAttribute(COUNT_PAGE, countPage);
        request.getSession().setAttribute(paginationName, pagination); //NOSONAR
        request.getSession().setAttribute(PAGINATION_BUTTON_ARRAY_ATTRIBUTE_NAME, paginationBtn);

    }

}
