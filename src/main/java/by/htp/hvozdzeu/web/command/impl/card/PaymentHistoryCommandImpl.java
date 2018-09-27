package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.PaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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

    /**
     * Max rows in report
     */
    private static final Integer MAX_COUNT_ROW_REPORT = 1000000000;

    /**
     * Min rows in report
     */
    private static final Integer MIN_COUNT_ROW_REPORT = 0;

    private static final String PAGINATION_REPORT = "paginationReport";
    private static final String SUM_REPORT = "sumReport";
    private static final String PAGINATION_NAME = "paymentHistory";
    private static final String PAGINATION_LIST_VALUE = "payment_history_pagination";
    private static final String PARAMETER_1 = "additional_param_1";
    private static final String PARAMETER_2 = "additional_param_2";
    private static final String PARAMETER_3 = "additional_param_3";
    private static final String RETURN_CARD_NUMBER = "returnCardNumber";
    private static final String RETURN_CARD_ID = "returnCardId";
    private static final String RETURN_CARD_DATE_START = "returnDateStart";
    private static final String RETURN_CARD_DATE_END = "returnDateEnd";

    private PaymentService paymentService = ServiceFactory.getPaymentService();
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

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

        CreditCard creditCard = creditCardService.findById(cardId);
        Integer count = paymentService.getAllPayments().size();
        Integer countRow = paymentService.findPaymentByCardAndBetweenDate(cardId, dateStart, dateEnd, count, 0).size();
        Integer countRowOnPage = getSessionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);
        Integer displacement = getSessionPaginationAttribute(request, countRow, DISPLACEMENT);
        List<PaymentReport> pagination = paymentService.findPaymentByCardAndBetweenDate(cardId, dateStart, dateEnd, countRowOnPage, displacement);
        List<PaymentReport> paymentReports = paymentService.findPaymentByCardAndBetweenDate(cardId, dateStart, dateEnd, MAX_COUNT_ROW_REPORT, MIN_COUNT_ROW_REPORT);

        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < paymentReports.size(); i++) {
            sum = sum.add(paymentReports.get(i).getAmountPayment());
        }

        writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);

        request.getSession().setAttribute(SUM_REPORT, sum);
        request.getSession().setAttribute(PAGINATION_REPORT, paymentReports);
        request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
        request.getSession().setAttribute(RETURN_CARD_NUMBER, creditCard.getCardNumber());
        request.getSession().setAttribute(RETURN_CARD_ID, cardId);
        request.getSession().setAttribute(RETURN_CARD_DATE_START, dateStart);
        request.getSession().setAttribute(RETURN_CARD_DATE_END, dateEnd);
        return PagePathConstantPool.PAYMENT_HISTORY_PAGINATION;
    }

}
