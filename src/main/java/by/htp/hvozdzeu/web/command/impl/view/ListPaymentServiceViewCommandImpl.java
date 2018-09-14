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

    private static final String PAGINATION_NAME = "paymentData";
    private static final String PAGINATION_LIST_VALUE = "list_services_view";
    private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Integer countRow = iPaymentDataService.read().size();
        Integer countRowOnPage = getSessionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);
        Integer displacement = getSessionPaginationAttribute(request, countRow, DISPLACEMENT);
        List<PaymentData> pagination = iPaymentDataService.pagination(countRowOnPage, displacement);
        writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);
        request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
        return PagePathConstantPool.LIST_SERVICE_VIEW;

    }
}
