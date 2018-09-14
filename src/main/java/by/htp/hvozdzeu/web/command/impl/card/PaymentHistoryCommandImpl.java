package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

import static by.htp.hvozdzeu.web.pagination.CalculatePagination.COUNT_ROW_ON_PAGE;
import static by.htp.hvozdzeu.web.pagination.CalculatePagination.DISPLACEMENT;
import static by.htp.hvozdzeu.web.pagination.NavigationEvent.PAGINATION_LIST;
import static by.htp.hvozdzeu.web.pagination.WriteSessionPagination.*;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_CARD_ID;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.DATE_END;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.DATE_START;

public class PaymentHistoryCommandImpl implements BaseCommand {

    private static final String PAGINATION_NAME = "paymentHistory";
    private static final String PAGINATION_LIST_VALUE = "payment_history_pagination";
    private static final String PARAMETER_1 = "additional_param_1";
    private static final String PARAMETER_2 = "additional_param_2";
    private static final String PARAMETER_3 = "additional_param_3";
    private static final String RETURN_CARD_NUMBER = "returnCardNumber";
    private static final String RETURN_CARD_ID = "returnCardId";
    private static final String RETURN_CARD_DATE_START = "returnDateStart";
    private static final String RETURN_CARD_DATE_END = "returnDateEnd";

    private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
    private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long cardId;
        if (request.getParameter(REQUEST_CARD_ID) != null && !request.getParameter(REQUEST_CARD_ID).isEmpty()) {
            cardId = Long.valueOf(request.getParameter(REQUEST_CARD_ID));
        } else {
            cardId = Long.valueOf(request.getParameter(PARAMETER_1));
        }

        LocalDate dateStart;
        if (request.getParameter(DATE_START) != null && !request.getParameter(DATE_START).isEmpty()) {
            dateStart = LocalDate.parse(request.getParameter(DATE_START));
        } else {
            dateStart = LocalDate.parse(request.getParameter(PARAMETER_2));
        }

        LocalDate dateEnd;
        if (request.getParameter(DATE_END) != null && !request.getParameter(DATE_END).isEmpty()) {
            dateEnd = LocalDate.parse(request.getParameter(DATE_END));
        } else {
            dateEnd = LocalDate.parse(request.getParameter(PARAMETER_3));
        }

        CreditCard creditCard = iCreditCardService.findById(cardId);
        Integer count = iPaymentService.read().size();
        Integer countRow = iPaymentService.findPaymentByCardAndBetweenDate(cardId, dateStart, dateEnd, count, 0).size();
        Integer countRowOnPage = getSessionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);
        Integer displacement = getSessionPaginationAttribute(request, countRow, DISPLACEMENT);
        List<PaymentReport> pagination = iPaymentService.findPaymentByCardAndBetweenDate(cardId, dateStart, dateEnd, countRowOnPage, displacement);
        writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);


        request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
        request.getSession().setAttribute(RETURN_CARD_NUMBER, creditCard.getCardNumber());
        request.getSession().setAttribute(RETURN_CARD_ID, cardId);
        request.getSession().setAttribute(RETURN_CARD_DATE_START, dateStart);
        request.getSession().setAttribute(RETURN_CARD_DATE_END, dateEnd);
        return PagePathConstantPool.PAYMENT_HISTORY_PAGINATION;
    }

}
