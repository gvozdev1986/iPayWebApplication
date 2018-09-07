package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.service.IPaymentDataService;
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

public class ListPaymentServiceViewCommandImpl implements BaseCommand {

    /**
     * Name list for display table in JSP
     */
    private static final String PAGINATION_NAME = "paymentData";

    /**
     * Command for location pagination in JSP
     */
    private static final String PAGINATION_LIST_VALUE = "list_services_view";

    /**
     * Service
     */
    private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        /**
         * Count row
         */
        Integer countRow = iPaymentDataService.read().size();

        /**
         * Count row on page
         */
        Integer countRowOnPage = getSessionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);

        /**
         * Displacement for navigation buttons
         */
        Integer displacement = getSessionPaginationAttribute(request, countRow, DISPLACEMENT);

        /**
         * Result pagination
         */
        List<PaymentData> pagination = iPaymentDataService.pagination(countRowOnPage, displacement);

        /**
         * Write pagination in session for display in JSP
         */
        writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);

        /**
         * Write dates for display pagination in JSP
         */
        request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);

        /**
         * Return to JSP
         */
        return PagePathConstantPool.LIST_SERVICE_VIEW;
    }
}
