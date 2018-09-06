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

public class PaymentHistoryCommandImpl implements BaseCommand {

    private static final String PAGINATION_NAME = "paymentHistory";
    private static final String PAGINATION_LIST_VALUE = "payment_history_pagination";
    private static final String CARD_ID_PARAM = "cardId";
    private static final String DATE_START_PARAM = "dateStart";
    private static final String DATE_END_PARAM = "dateEnd";
    private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
    private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long cardId;
        if(request.getParameter(CARD_ID_PARAM) != null && !request.getParameter(CARD_ID_PARAM).isEmpty()){
            cardId = Long.valueOf(request.getParameter(CARD_ID_PARAM));
        } else {
            cardId = Long.valueOf(request.getParameter("additional_param_1"));
        }

        LocalDate dateStart;
        if(request.getParameter(DATE_START_PARAM) != null && !request.getParameter(DATE_START_PARAM).isEmpty()){
            dateStart = LocalDate.parse(request.getParameter(DATE_START_PARAM));
        } else {
            dateStart = LocalDate.parse(request.getParameter("additional_param_2"));
        }

        LocalDate dateEnd;
        if(request.getParameter(DATE_END_PARAM) != null && !request.getParameter(DATE_END_PARAM).isEmpty()){
            dateEnd = LocalDate.parse(request.getParameter(DATE_END_PARAM));
        } else {
            dateEnd = LocalDate.parse(request.getParameter("additional_param_3"));
        }

         /**
         * Get number of credit card
         */
        CreditCard creditCard = iCreditCardService.findById(cardId);

        /**
         * Count row
         */
        Integer count = iPaymentService.read().size();

        Integer countRow = iPaymentService.findPaynemtByCardAndBetweenDate(cardId, dateStart, dateEnd, count, 0).size();

        /**
         * Count row on page
         */
        Integer countRowOnPage = getSessionPaginationAttribute(request, countRow, COUNT_ROW_ON_PAGE);

        /**
         * Displacement for navigation buttons
         */
        Integer displacement = getSessionPaginationAttribute(request, countRow, DISPLACEMENT);

        /**
         * get pagination
         */
        List<PaymentReport> pagination = iPaymentService.findPaynemtByCardAndBetweenDate(cardId, dateStart, dateEnd, countRowOnPage, displacement);

        /**
         * write pagination to session
         */
        writeSessionPagination(request, countRow, PAGINATION_NAME, pagination);


        request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
        request.getSession().setAttribute("returnCardNumber", creditCard.getCardNumber());
        request.getSession().setAttribute("returnCardId", cardId);
        request.getSession().setAttribute("returnDateStart", dateStart);
        request.getSession().setAttribute("returnDateEnd", dateEnd);
        return PagePathConstantPool.PAYMENT_HISTORY_PAGINATION;
    }

}
